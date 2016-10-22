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

import io.alicorn.data.jongothings.JongoDriver;
import io.alicorn.data.models.Client;

import javax.inject.Inject;
import javax.inject.Singleton;
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

    private Map<String, String> emailToTokenMap = new ConcurrentHashMap<>();
    private Map<String, String> tokenToIdMap = new ConcurrentHashMap<>();
    private Map<Thread, String> threadToIdMap = new ConcurrentHashMap<>();

    private String getTokenForUser(String email, boolean asAgent) {
        if (asAgent) {
            Agent agent = JongoDriver.getCollection("agents").findOne("{email:#}", email).as(Agent.class);
            if (emailToTokenMap.containsKey(email)) {
                return emailToTokenMap.get(email);
            } else {
                String uuid = UUID.randomUUID().toString();
                emailToTokenMap.put(email, uuid);
                return uuid;
            }
        } else {
            Client client = JongoDriver.getCollection("clients").findOne("{email:#}", email).as(Client.class);
            if (emailToTokenMap.containsKey(email)) {
                return emailToTokenMap.get(email);
            } else {
                String uuid = UUID.randomUUID().toString();
                emailToTokenMap.put(email, uuid);
                return uuid;
            }
        }

        return "";
    }

    @Inject
    public LoginEndpoint(SparkWrapper sparkWrapper) {

        // Clients ////////////////////////////////////////////////////////////
        sparkWrapper.post("/api/user/client/token", (req, res) -> {

        });

        // Agents /////////////////////////////////////////////////////////////
        sparkWrapper.post("/api/user/agent/token", (req, res) -> {

        });
    }
}
