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
import io.alicorn.data.models.Continuum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

public class CocEndpoint {
    private static final Logger logger = LoggerFactory.getLogger(CocEndpoint.class);

    private static final ObjectMapper objectMapper = new ObjectMapper();
    @Inject
    public CocEndpoint(SparkWrapper sparkWrapper, CocDbFacade cocDbFacade, LoginEndpoint loginEndpoint) {

        // Create / Update
        sparkWrapper.post("/api/cocs", (req, res) -> {
            if (loginEndpoint.isCurrentUserAgent()) {
                Continuum continuum = objectMapper.readValue(
                        Json.parse(req.body()).asObject().get("coc").toString(),
                        Continuum.class);

                cocDbFacade.setCoc(continuum);
                return new WebserviceResponse().toString();
            } else {
                return new WebserviceResponse().addError("Current user is not authorized to create and/or update a continuum.");
            }
        });

        // Retrieve
        sparkWrapper.get("/api/cocs/:uuid", (req, res) -> {
            Continuum continuum = cocDbFacade.getContinuum(req.params(":uuid"));
            if (continuum != null) {
                return new WebserviceResponse().set("coc", Json.parse(objectMapper.writeValueAsString(continuum)));
            } else {
                return new WebserviceResponse().addError("No continuum exists with UUID: " + req.params(":uuid"));
            }
        });

        // Delete
        sparkWrapper.post("/api/coc/delete", (req, res) -> {
            String uuidToDelete = Json.parse(req.body()).asObject().get("uuid").asString();
            if (loginEndpoint.isCurrentUserAgent()) {
                cocDbFacade.deleteContinuum(uuidToDelete);
                return new WebserviceResponse().toString();
            } else {
                return new WebserviceResponse().addError("You can't do that, fam.");
            }
        });

        // Get all the continuum
        sparkWrapper.get("/api/cocs", (req, res) -> {
            JsonArray cocs = new JsonArray();
            cocDbFacade.getAllContinuum().forEachRemaining((coc) -> {
                try {
                    cocs.add(Json.parse(objectMapper.writeValueAsString(coc)));
                } catch (Exception ex) {
                    logger.warn(ex.getMessage(), ex);
                }
            });
            return new WebserviceResponse().set("cocs", cocs).toString();
        });

        // TODO: JOOOOOOOOOOOSH
        sparkWrapper.get("/api/coc/getCocByService", (req, res) -> {
            cocDbFacade.getAllContinuum().forEachRemaining((coc) -> {

            });

            return "";
        });
    }
}
