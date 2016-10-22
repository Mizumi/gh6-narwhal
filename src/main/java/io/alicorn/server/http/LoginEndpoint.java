/*
 * Project: gh6
 * Since: Oct 22, 2016
 *
 * Copyright (c) Brandon Sanders [brandon@alicorn.io]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.alicorn.server.http;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.alicorn.data.jongothings.UsersDbFacade;
import io.alicorn.data.models.Agent;
import io.alicorn.data.models.Client;
import io.alicorn.data.models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * TODO:
 *
 * @author Brandon Sanders [brandon@alicorn.io]
 */
@Singleton
public class LoginEndpoint {

    private static final Logger logger = LoggerFactory.getLogger(LoginEndpoint.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private final UsersDbFacade usersDbFacade;
    private Map<String, String> emailToTokenMap = new ConcurrentHashMap<>();
    private Map<String, User> tokenToUserMap = new ConcurrentHashMap<>();
    private Map<Thread, User> threadToUserMap = new ConcurrentHashMap<>();

    public static final Charset charset = Charset.forName("ISO-8859-1");

    public static String hash(byte[] bytes) {
        try {
            //Create the message digest.
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            digest.update(bytes);

            //Hash the bytes.
            byte[] hashed = digest.digest();

            //Translate the bytes into a hexadecimal string.
            StringBuilder hexString = new StringBuilder();
            for (byte aHashed : hashed) {
                String hex = Integer.toHexString(0xff & aHashed);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String hash(char[] chars) {
        //Parse chars into bytes for hashing.
        CharBuffer charBuffer = CharBuffer.wrap(chars);
        ByteBuffer byteBuffer = charset.encode(charBuffer);
        byte[] bytes = Arrays.copyOfRange(byteBuffer.array(),
                                          byteBuffer.position(),
                                          byteBuffer.limit());

        //Clear temporary arrays of any data.
        Arrays.fill(charBuffer.array(), '\u0000');
        Arrays.fill(byteBuffer.array(), (byte) 0);

        //Generate the SHA-256 hash.
        String hash = hash(bytes);

        //Clear remaining arrays of any data.
        Arrays.fill(bytes, (byte) 0);

        return hash;
    }

    public static String hash(String string) {
        return hash(string.getBytes(charset));
    }

    private String getTokenForUser(String email, String key, boolean asAgent) {
        User user;
        if (asAgent) {
            user = usersDbFacade.getAgentByEmail(email);
        }  else {
            user = usersDbFacade.getClientByEmail(email);
        }

        if (user != null && user.getKey().equals(hash(key))) {
            if (emailToTokenMap.containsKey(email)) {
                return new WebserviceResponse().set("token", emailToTokenMap.get(email)).toString();
            } else {
                String uuid = UUID.randomUUID().toString();
                emailToTokenMap.put(email, uuid);
                tokenToUserMap.put(uuid, user);
                return new WebserviceResponse().set("token", uuid).toString();
            }
        } else {
            return new WebserviceResponse().addError("Specified user does not exist or an incorrect password was given.").toString();
        }
    }

    @Inject
    public LoginEndpoint(SparkWrapper sparkWrapper, UsersDbFacade usersDbFacade) {

        this.usersDbFacade = usersDbFacade;

        sparkWrapper.exception(Exception.class, (e, req, res) -> logger.error("Spark Error: " + e.getMessage(), e));

        sparkWrapper.before((req, res) -> {
            try {
                JsonObject json = Json.parse(req.body()).asObject();
                if (json.get("token") != null) {
                    if (tokenToUserMap.containsKey(json.get("token").asString())) {
                        threadToUserMap.put(Thread.currentThread(), tokenToUserMap.get(json.get("token").asString()));
                    }
                }
            } catch (Exception e) {
                if (!e.getMessage().contains("Unexpected end of input at 1:-1")) {
                    logger.warn(e.getMessage(), e);
                }
            }
        });

        sparkWrapper.after((req, res) -> {
            if (threadToUserMap.containsKey(Thread.currentThread())) {
                threadToUserMap.remove(Thread.currentThread());
            }
        });

        // Clients ////////////////////////////////////////////////////////////
        sparkWrapper.post("/api/user/client/login", (req, res) -> {
            JsonObject json = Json.parse(req.body()).asObject();
            return getTokenForUser(json.get("email").asString(), json.get("key").asString(), false);
        });

        sparkWrapper.post("/api/user/client/logout", (req, res) -> {
            JsonObject json = Json.parse(req.body()).asObject();
            String email = json.get("email").asString();
            String token = emailToTokenMap.get(email);
            emailToTokenMap.remove(email);
            tokenToUserMap.remove(token);
            return new WebserviceResponse().toString();
        });

        sparkWrapper.post("/api/user/clients", (req, res) -> {
            Client client = objectMapper.readValue(Json.parse(req.body()).asObject().get("client").toString(),
                                                         Client.class);
            if (client.getEmail() != null && client.getKey() != null) {
                client.setKey(hash(client.getKey()));
                if (usersDbFacade.getClientByEmail(client.getEmail()) == null) {
                    usersDbFacade.setClient(client);
                    return new WebserviceResponse().toString();
                } else {
                    return new WebserviceResponse().addError("Another client already exists with the given email address.").toString();
                }
            } else {
                return new WebserviceResponse().addError("Provided client must have an email and a key (password) field.").toString();
            }
        });

        sparkWrapper.get("/api/user/clients", (req, res) -> {
            if (isCurrentUserAgent()) {
                JsonArray users = new JsonArray();
                ObjectMapper objectMapper = new ObjectMapper();
                usersDbFacade.getAllClients().forEachRemaining((client) -> {
                    try {
                        users.add(Json.parse(objectMapper.writeValueAsString(client)).asObject().remove("key"));
                    } catch (Exception e) {
                        logger.warn(e.getMessage(), e);
                    }
                });
                return new WebserviceResponse().set("clients", users).toString();
            } else {
                return new WebserviceResponse().addError("Only registered agents may view users on this HMIS.");
            }
        });

        // Agents /////////////////////////////////////////////////////////////
        sparkWrapper.post("/api/user/agent/login", (req, res) -> {
            JsonObject json = Json.parse(req.body()).asObject();
            return getTokenForUser(json.get("email").asString(), json.get("key").asString(), true);
        });

        sparkWrapper.post("/api/user/agent/logout", (req, res) -> {
            JsonObject json = Json.parse(req.body()).asObject();
            String email = json.get("email").asString();
            String token = emailToTokenMap.get(email);
            emailToTokenMap.remove(email);
            tokenToUserMap.remove(token);
            return new WebserviceResponse().toString();
        });

        // TODO: Copy-pasta from above.
        sparkWrapper.post("/api/user/agents", (req, res) -> {
            if (isCurrentUserAgent()) {
                Agent agent = objectMapper.readValue(
                        Json.parse(req.body()).asObject().get("agent").toString(),
                        Agent.class);
                if (agent.getEmail() != null && agent.getKey() != null) {
                    agent.setKey(hash(agent.getKey()));
                    if (usersDbFacade.getAgentByEmail(agent.getEmail()) == null) {
                        usersDbFacade.setAgent(agent);
                        return new WebserviceResponse().toString();
                    } else {
                        return new WebserviceResponse().addError("Another agent already exists with the given email address.").toString();
                    }
                } else {
                    return new WebserviceResponse().addError("Provided agent must have an email and a key (password) field.").toString();
                }
            } else {
                return new WebserviceResponse().addError("Current agent is not authorized to create a new agent.").toString();
            }
        });

        sparkWrapper.get("/api/user/agents", (req, res) -> {
            if (isCurrentUserAgent()) {
                JsonArray users = new JsonArray();
                ObjectMapper objectMapper = new ObjectMapper();
                usersDbFacade.getAllAgents().forEachRemaining((client) -> {
                    try {
                        users.add(Json.parse(objectMapper.writeValueAsString(client)).asObject().remove("key"));
                    } catch (Exception e) {
                        logger.warn(e.getMessage(), e);
                    }
                });
                return new WebserviceResponse().set("agents", users).toString();
            } else {
                return new WebserviceResponse().addError("Only registered agents may view users on this HMIS.");
            }
        });
    }

    public boolean hasCurrentUser() {
        return threadToUserMap.containsKey(Thread.currentThread());
    }

    public User getCurrentUser() {
        return threadToUserMap.get(Thread.currentThread());
    }

    public boolean isCurrentUserAgent() {
        return hasCurrentUser() && getCurrentUser().getKind().equals(User.Kind.AGENT);
    }
}
