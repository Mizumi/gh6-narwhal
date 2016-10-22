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

import io.alicorn.data.models.services.Service;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Iterator;

/**
 * TODO:
 *
 * @author Brandon Sanders [brandon@alicorn.io]
 */
@Singleton
public class ServiceDbFacade {

    public static final String SERVICE_COLLECTION = "Services";

    @Inject
    public ServiceDbFacade() { }

    public void setService(Service service) {
        JongoDriver.getCollection(SERVICE_COLLECTION).update("{uuid:#}", service.getUuid()).upsert().with(service);
    }

    public Service getService(String uuid) {
        return JongoDriver.getCollection(SERVICE_COLLECTION).findOne("{uuid:#}", uuid).as(Service.class);
    }

    public Iterator<Service> getAllServices() {
        return JongoDriver.getCollection(SERVICE_COLLECTION).find().as(Service.class);
    }
}
