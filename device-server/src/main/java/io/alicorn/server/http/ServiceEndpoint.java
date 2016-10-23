/*
    Alicorn Systems GH6 Project "Narwhal"

    Copyright (c) Brandon Sanders [brandon@alicorn.io], Joshua Gagen [joshuagagen@outlook.com],
                  Edwin Munguia [daiokaio@gmail.com] and Justin Stone

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
 */
package io.alicorn.server.http;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonArray;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.alicorn.data.jongothings.CocDbFacade;
import io.alicorn.data.jongothings.ServiceDbFacade;
import io.alicorn.data.models.services.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ServiceEndpoint {
    private static final Logger logger = LoggerFactory.getLogger(ServiceEndpoint.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Inject
    public ServiceEndpoint(SparkWrapper spark, ServiceDbFacade serviceDbFacade, LoginEndpoint loginEndpoint, CocDbFacade cocDbFacade) {

        // Create / Update
        spark.post("/api/services", (req, res) -> {
            if (loginEndpoint.isCurrentUserAgent()) {
                Service service = objectMapper.readValue(
                        Json.parse(req.body()).asObject().get("service").toString(),
                        Service.class);

                serviceDbFacade.setService(service);
                return new WebserviceResponse().toString();
            } else {
                return new WebserviceResponse().addError("Current user is not authorized to create and/or update a service");
            }
        });

        // Retrieve
        spark.get("/api/services/:uuid", (req, res) -> {
            Service service = serviceDbFacade.getService(req.params(":uuid"));
            if (service != null) {
                return new WebserviceResponse().set("service", Json.parse(objectMapper.writeValueAsString(service)));
            } else {
                return new WebserviceResponse().addError("No continuum exists with UUID: " + req.params(":uuid"));
            }
        });

        // Delete
        spark.post("/api/service/delete", (req, res) -> {
            String uuidToDelete = Json.parse(req.body()).asObject().get("uuid").asString();
            if (loginEndpoint.isCurrentUserAgent()) {
                serviceDbFacade.deleteService(uuidToDelete);
                return new WebserviceResponse().toString();
            }
            return new WebserviceResponse().addError("Ah ah ah! You didn't say the magic word.");
        });

        // Get all for continuum
        spark.post("/api/service/getServicesForCoc", (req, res) -> {
            StringBuilder sb = new StringBuilder().append("[ ");
            JsonArray uuids = Json.parse(req.body()).asObject().get("uuids").asArray();
            for (int i = 0; i < uuids.size(); i++) {
                sb.append(uuids.get(i));
                if (i < uuids.size() - 1) {
                    sb.append(", ");
                }
            }
            sb.append(" ]");

            JsonArray services = new JsonArray();
            serviceDbFacade.getAllInList(sb.toString()).forEachRemaining((service -> {
                try {
                    services.add(Json.parse(objectMapper.writeValueAsString(service)));
                } catch (Exception ex) {
                    logger.warn(ex.getMessage(), ex);
                }
            }));
            return new WebserviceResponse().set("services", services).toString();
        });

//        spark.post("/api/service/getCocsForServices", (req, res) -> {
//            JsonArray cocs = new JsonArray();
//            Service.ServiceType type = Service.ServiceType.valueOf(Json.parse(req.body()).asObject().get("serviceType").asString());
//
//            cocDbFacade.getAllContinuum().forEachRemaining((continuum) -> {
//                for (int i = 0; i < continuum.getServices().size(); i++) {
//                    Service service = continuum.getServices().get(i);
//                    if (service.getServiceType().equals(type)) {
//                        try {
//                            cocs.add(Json.parse(objectMapper.writeValueAsString(continuum)));
//                            break;
//                        } catch (Exception ex) {
//                            logger.warn(ex.getMessage(), ex);
//                        }
//                    }
//                }
//            });
//
//            return new WebserviceResponse().set("cocs", cocs).toString();
//        });
    }
}
