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
package io.alicorn.server;

import io.alicorn.data.jongothings.UsersDbFacade;
import io.alicorn.server.sms.TwilioSMSClient;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

/**
 * TODO:
 *
 * @author Brandon Sanders [brandon@alicorn.io]
 */
@Singleton
public class NotificationService {
//Private//////////////////////////////////////////////////////////////////////

    private final UsersDbFacade usersDbFacade;
    private final TwilioSMSClient smsClient;

//Protected////////////////////////////////////////////////////////////////////

//Public///////////////////////////////////////////////////////////////////////

    @Inject
    public NotificationService(TwilioSMSClient smsClient, UsersDbFacade usersDbFacade) {
        this.smsClient = smsClient;
        this.usersDbFacade = usersDbFacade;
    }

    public void sendNotificationToClients(String message, List<String> emails) {
        usersDbFacade.getAllClients().forEachRemaining((client) -> {
            if (emails.contains(client.getEmail())) {
                if (client.getPhoneNumber() != null && !client.getPhoneNumber().isEmpty()) {
                    smsClient.sendSMSMessage(client.getPhoneNumber(), message);
                }
            }
        });
    }

    public void sendNotificationToClients(String message) {
        usersDbFacade.getAllClients().forEachRemaining((client) -> {
            if (client.getPhoneNumber() != null && !client.getPhoneNumber().isEmpty()) {
                smsClient.sendSMSMessage(client.getPhoneNumber(), message);
            }
        });
    }
}
