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

import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;
import io.alicorn.data.models.User;
import io.alicorn.server.NotificationService;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;

/**
 * TODO:
 *
 * @author Brandon Sanders [brandon@alicorn.io]
 */
@Singleton
public class NotificationsEndpoint {
//Private//////////////////////////////////////////////////////////////////////

//Protected////////////////////////////////////////////////////////////////////

//Public///////////////////////////////////////////////////////////////////////

    @Inject
    public NotificationsEndpoint(LoginEndpoint loginEndpoint, SparkWrapper sparkWrapper, NotificationService notificationService) {
        sparkWrapper.post("/api/user/client/notifyAll", (req, res) -> {
            if (loginEndpoint.isCurrentUserAgent()) {
                String notification = JsonObject.readFrom(req.body()).get("notification").asString();
                notificationService.sendNotificationToClients(notification);
                return new WebserviceResponse().toString();
            } else {
                return new WebserviceResponse().addError("Notifications may only be sent by logged in agents.");
            }
        });

        sparkWrapper.post("/api/user/client/notifyList", (req, res) -> {
            if (loginEndpoint.isCurrentUserAgent()) {
                JsonObject json = JsonObject.readFrom(req.body());
                String notification = json.get("notification").asString();
                List<String> emails = new ArrayList<>();
                for (JsonValue email : json.get("emails").asArray()) {
                    emails.add(email.asString());
                }
                notificationService.sendNotificationToClients(notification, emails);
                return new WebserviceResponse().toString();
            } else {
                return new WebserviceResponse().addError("Notifications may only be sent by logged in agents.");
            }
        });
    }
}
