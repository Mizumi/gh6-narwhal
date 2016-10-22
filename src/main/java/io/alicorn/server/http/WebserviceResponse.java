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

import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TODO:
 *
 * @author Brandon Sanders [brandon@alicorn.io]
 */
public class WebserviceResponse {
    private static final Logger logger = LoggerFactory.getLogger(WebserviceResponse.class);

    private JsonArray errors = new JsonArray();
    private JsonObject response = new JsonObject();

    public WebserviceResponse addError(String error) {
        errors.add(error);
        return this;
    }

    // If this line number changes, also change the error message below.
    public WebserviceResponse set(String property, Object value) {
        if (value instanceof String) {
            response.set(property, (String) value);
        } else if (value instanceof Boolean) {
            response.set(property, (Boolean) value);
        } else if (value instanceof JsonValue) {
            response.set(property, (JsonValue) value);
        } else {
            //TODO: #hackathonLife
            logger.error("Couldn't set property on webservice response; it isn't a supported type. Please add it around line 42 of io.alicorn.server.http.WebserviceResponse.");
        }

        return this;
    }

    public String toString() {
        response.set("errors", errors);
        return response.toString();
    }
}
