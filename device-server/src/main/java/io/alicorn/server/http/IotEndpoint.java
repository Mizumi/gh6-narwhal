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

import io.alicorn.data.jongothings.ServiceDbFacade;
import io.alicorn.data.models.services.Service;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * TODO:
 *
 * @author Brandon Sanders [brandon@alicorn.io]
 */
@Singleton
public class IotEndpoint {

    // Split on ::;, add six spaces to end.
    private final String[] messages = new String[] {
        "8 Beds Available // Corner of Convention Plaza and Tucker Blvd      ::St.Pat. Center",
        "Dinner Time @ 6PM // Across from the Arch      ::Imaginary Place",
        "Womens' Shelter // 3200 St. Vincent Ave      ::Almost Home"
    };

    private final int messagesMask = messages.length;

    private Random random = new Random();
    private long timer = System.currentTimeMillis();
    private long cursor = 0;
    private String message = messages[0];

    @Inject
    public IotEndpoint(SparkWrapper sparkWrapper, ServiceDbFacade serviceDbFacade) {

        sparkWrapper.get("/api/iot/narwhalText", (req, res) -> {
            if (System.currentTimeMillis() > timer + 30000) {
                timer = System.currentTimeMillis();

                if (cursor % 2 == 0) {
                    List<Service> list = new ArrayList<>();
                    serviceDbFacade.getAll().forEachRemaining(list::add);
                    Service service = list.get(random.nextInt(list.size()));
                    message = service.getServiceType().name() + " // " + service.getContactInfo().getAddress() + "     ::" + service.getContactInfo().getName();
                } else {
                    message = messages[(int) (cursor % messagesMask)];
                }

                cursor += 1;
            }

            return message;
        });
    }
}
