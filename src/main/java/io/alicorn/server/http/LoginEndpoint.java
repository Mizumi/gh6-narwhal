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

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * TODO:
 *
 * @author Brandon Sanders [brandon@alicorn.io]
 */
@Singleton
public class LoginEndpoint {

    private Map<String, String> idToTokenMap = new ConcurrentHashMap<>();
    private Map<String, String> tokenToIdMap = new ConcurrentHashMap<>();
    private Map<Thread, String> threadToIdMap = new ConcurrentHashMap<>();

    private String getTokenForUser(String id, boolean isAgent) {
        if (isAgent) {
            JongoDriver.getCollection("agents").findOne("{id:#}", id).as(Agent.class);
        } else {
            JongoDriver.getCollection("clients").findOne("{id:#}", id).as(Client.class);
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
