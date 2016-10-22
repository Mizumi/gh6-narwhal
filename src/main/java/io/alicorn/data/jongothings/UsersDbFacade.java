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
package io.alicorn.data.jongothings;

import io.alicorn.data.models.Agent;
import io.alicorn.data.models.Client;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Iterator;

/**
 * Utils for scanning all models in the Mongo database.
 *
 * @author Brandon Sanders [brandon@alicorn.io]
 */
@Singleton
public class UsersDbFacade {

    public static final String AGENTS_COLLECTION = "Agents";
    public static final String CLIENTS_COLLECTION = "Clients";

    @Inject
    public UsersDbFacade() { }

    public void setAgent(Agent agent) {
        JongoDriver.getCollection(AGENTS_COLLECTION).update("{email:#}", agent.getEmail()).upsert().with(agent);
    }

    public Agent getAgent(String uuid) {
        return JongoDriver.getCollection(AGENTS_COLLECTION).findOne("{uuid:#}", uuid).as(Agent.class);
    }

    public Agent getAgentByEmail(String email) {
        return JongoDriver.getCollection(AGENTS_COLLECTION).findOne("{email:#}", email).as(Agent.class);
    }

    public Iterator<Agent> getAllAgents() {
        return JongoDriver.getCollection(AGENTS_COLLECTION).find().as(Agent.class);
    }

    public void setClient(Client client) {
        JongoDriver.getCollection(CLIENTS_COLLECTION).update("{email:#}", client.getEmail()).upsert().with(client);
    }

    public Client getClient(String uuid) {
        return JongoDriver.getCollection(CLIENTS_COLLECTION).findOne("{uuid:#}", uuid).as(Client.class);
    }

    public Client getClientByEmail(String email) {
        return JongoDriver.getCollection(CLIENTS_COLLECTION).findOne("{email:#}", email).as(Client.class);
    }

    public Iterator<Client> getAllClients() {
        return JongoDriver.getCollection(CLIENTS_COLLECTION).find().as(Client.class);
    }
}
