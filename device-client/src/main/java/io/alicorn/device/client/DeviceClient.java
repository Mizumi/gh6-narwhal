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
package io.alicorn.device.client;

import io.alicorn.device.client.grove.DisplayTools;
import io.alicorn.device.client.i2c.I2CNativeLinuxBacking;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Runtime for an Alicorn IoT device client. Due to hackathon code and time
 * restrictions, this does not make use of the normal Alicorn or Pronghorn
 * APIs and instead uses a modified version of a legacy Pronghorn internal API
 * for handling I2C comms with devices. It's gr8 m8, I r8 8/8.
 *
 * @author Brandon Sanders [brandon@alicorn.io]
 */
public class DeviceClient {
    private static final Logger logger = LoggerFactory.getLogger(DeviceClient.class);

    // Create a connection to the native Linux I2C lines.
    private static final I2CNativeLinuxBacking i2c = new I2CNativeLinuxBacking((byte)1);

    public static final int DISPLAY_WIDTH = 16;
    public static final int DISPLAY_HEIGHT = 2;

    private static void transform3xWrite(byte[] command) {
        assert command.length % 3 == 0;
        for (int i = 0; i < command.length; i += 3) {
            i2c.write(command[i], new byte[]{command[i + 1], command[i + 2]}, 2);
        }
    }

    private static String apacheHttpEntityToString(HttpEntity entity) {
        try {
            if (entity != null) {
                String encoding = "UTF-8";
                if (entity.getContentEncoding() != null) {
                    encoding = entity.getContentEncoding().getValue();
                    encoding = encoding == null ? "UTF-8" : encoding;
                }
                return EntityUtils.toString(entity, encoding);
            } else {
                logger.error("Cannot parse a null ApacheHttpEntity.");
            }
        } catch (IOException e) {
            logger.error("Unexpected IO error when parsing entity content [" + e.getMessage() + "].", e);
        }

        return "";
    }

    public static void main(String[] args) {
        logger.info("Starting Alicorn Client System");

        // Prepare Display Color.
        transform3xWrite(DisplayTools.commandForColor(100, 50, 150));

        // Setup text information.
//        transform3xWrite(DisplayTools.commandForText("Sup Fam"));

        class StringWrapper {
            public String string = "";
        } final StringWrapper string = new StringWrapper();

        // Text Handler.
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                String latestString = "";
                String outputStringLine1Complete = "";
                long outputStringLine1Cursor = 1;
                int outputStringLine1Mask = 0;
                String outputStringLine2 = "";

                while (true) {
                    if (!latestString.equals(string.string)) {
                        latestString = string.string;
                        String[] latestStrings = latestString.split("::");
                        outputStringLine1Complete = latestStrings[0];
                        outputStringLine1Mask = outputStringLine1Complete.length();
                        outputStringLine1Cursor = 0;

                        // Trim second line to a length of sixteen.
                        outputStringLine2 = latestStrings.length > 1 ? latestStrings[1] : "";
                        if (outputStringLine2.length() > 16) {
                            outputStringLine2 = outputStringLine2.substring(0, 16);
                        }
                    }

                    StringBuilder outputStringLine1 = new StringBuilder();
                    if (outputStringLine1Complete.length() > 0) {
                        long cursor = outputStringLine1Cursor;
                        for (int i = 0; i < 16; i++) {
                            outputStringLine1.append(outputStringLine1Complete.charAt((int) (cursor % outputStringLine1Mask)));
                            cursor += 1;
                        }
                        outputStringLine1Cursor += 1;
                    } else {
                        outputStringLine1.append("                ");
                    }

                    try {
                        transform3xWrite(DisplayTools.commandForText(outputStringLine1.toString() + outputStringLine2));
                        Thread.sleep(400);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.start();

        // Event Handler
        while (true) {
            try {
                String url = "http://169.254.75.84:9789/api/iot/narwhalText";
                HttpClient client = HttpClientBuilder.create().build();
                HttpGet request = new HttpGet(url);
                HttpResponse response = client.execute(request);
                string.string = apacheHttpEntityToString(response.getEntity());

                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
