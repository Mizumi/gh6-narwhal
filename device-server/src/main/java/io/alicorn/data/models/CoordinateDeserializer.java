package io.alicorn.data.models;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.io.IOException;

public class CoordinateDeserializer extends JsonDeserializer<GeospatialCoordinate> {
    @Override
    public GeospatialCoordinate deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        GeospatialCoordinate geospatialCoordinate = new GeospatialCoordinate();
        JsonNode jn = jsonParser.getCodec().readTree(jsonParser);
        geospatialCoordinate.setLongitude(jn.get("coordinates").get(0).asDouble());
        geospatialCoordinate.setLatitude(jn.get("coordinates").get(1).asDouble());
        return geospatialCoordinate;
    }
}
