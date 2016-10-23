package io.alicorn.data.models;

import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;

public class GeospatialCoordinate {

    private Double latitude;
    private Double longitude;

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
