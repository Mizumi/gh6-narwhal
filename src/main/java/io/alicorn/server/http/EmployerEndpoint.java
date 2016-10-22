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
import com.fasterxml.jackson.databind.ObjectMapper;
import io.alicorn.data.jongothings.EmployerDbFacade;
import io.alicorn.data.models.services.externalized.inner.classes.to.make.brandon.happy.Employer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * TODO:
 *
 * @author Brandon Sanders [brandon@alicorn.io]
 */
@Singleton
public class EmployerEndpoint {

    private static final Logger logger = LoggerFactory.getLogger(EmployerEndpoint.class);

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Inject
    public EmployerEndpoint(SparkWrapper sparkWrapper, EmployerDbFacade employerDbFacade, LoginEndpoint loginEndpoint) {
        sparkWrapper.post("/api/employers", (req, res) -> {
            if (loginEndpoint.isCurrentUserAgent()) {
                Employer employer = objectMapper.readValue(Json.parse(req.body()).asObject().get("employer").toString(), Employer.class);

                employerDbFacade.setEmployer(employer);
                return new WebserviceResponse().toString();
            } else {
                return new WebserviceResponse().addError("Current user is not authorized to create and/or update an employer.");
            }
        });

        sparkWrapper.get("/api/employers/:uuid", (req, res) -> {
            Employer employer = employerDbFacade.getEmployer(req.params(":uuid"));
            if (employer != null) {
                return new WebserviceResponse().set("employer", Json.parse(objectMapper.writeValueAsString(employer)));
            } else {
                return new WebserviceResponse().addError("No employer exists with UUID: " + req.params(":uuid"));
            }
        });

        sparkWrapper.post("/api/employer/delete", (req, res) -> {
            String uuidToDelete = Json.parse(req.body()).asObject().get("uuid").asString();
            if (loginEndpoint.isCurrentUserAgent()) {
                employerDbFacade.deleteEmployer(uuidToDelete);
                return new WebserviceResponse().toString();
            } else {
                return new WebserviceResponse().addError("You can't do that, fam.");
            }
        });

        sparkWrapper.get("/api/employers", (req, res) -> {
            JsonArray employers = new JsonArray();
            employerDbFacade.getAllEmployers().forEachRemaining((employer) -> {
                try {
                    employers.add(Json.parse(objectMapper.writeValueAsString(employer)));
                } catch (Exception ex) {
                    logger.warn(ex.getMessage(), ex);
                }
            });
            return new WebserviceResponse().set("employers", employers).toString();
        });
    }
}
