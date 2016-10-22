/*
 * Project: gh6
 * Since: Oct 21, 2016
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
package io.alicorn.server.sms;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import io.alicorn.Config;
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
public class TwilioSMSClient {
//Private//////////////////////////////////////////////////////////////////////

    private static final Logger logger = LoggerFactory.getLogger(TwilioSMSClient.class);

    private final String ACCOUNT_SID;
    private final String AUTH_TOKEN;

//Protected////////////////////////////////////////////////////////////////////

//Public///////////////////////////////////////////////////////////////////////

    public static final String TWILIO_PHONE_NUMBER = "+13143100157";

    @Inject
    public TwilioSMSClient(Config config) {
        ACCOUNT_SID = config.getTwilioAccountSID();
        AUTH_TOKEN = config.getTwilioAuthToken();

        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
    }

    /**
     * Sends an SMS text message to a phone.
     *
     * @param toNumber Phone number to send the SMS message to. Prefix with +1 for NA numbers.
     * @param message Message to send.
     *
     * @return The Twilio {@link Message} response.
     */
    public Message sendSMSMessage(String toNumber, String message) {
        logger.info("Sending message {} to {}", message, toNumber);
        return Message
                .creator(new PhoneNumber(toNumber),
                         new PhoneNumber(TWILIO_PHONE_NUMBER),
                         message).create();
    }
}
