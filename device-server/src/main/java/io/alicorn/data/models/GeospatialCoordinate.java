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
