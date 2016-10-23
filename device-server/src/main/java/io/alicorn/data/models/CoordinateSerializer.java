package io.alicorn.data.models;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class CoordinateSerializer extends JsonSerializer<GeospatialCoordinate> {
    @Override
    public void serialize(GeospatialCoordinate geospatialCoordinate, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
            throws IOException, JsonProcessingException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("type", "Point");
        jsonGenerator.writeFieldName("coordinates");
        jsonGenerator.writeStartArray();
        jsonGenerator.writeNumber(geospatialCoordinate.getLongitude());
        jsonGenerator.writeNumber(geospatialCoordinate.getLongitude());
        jsonGenerator.writeEndArray();
        jsonGenerator.writeEndObject();
    }
}
