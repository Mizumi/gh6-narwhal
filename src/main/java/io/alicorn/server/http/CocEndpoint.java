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
        sparkWrapper.post("/api/coc", (req, res) -> {
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
        sparkWrapper.get("/api/coc/:uuid", (req, res) -> {
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
            }
            return null;
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
    }
}