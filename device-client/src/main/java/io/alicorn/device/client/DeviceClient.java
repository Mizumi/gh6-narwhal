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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    public static void transform3xWrite(byte[] command) {
        assert command.length % 3 == 0;
        for (int i = 0; i < command.length; i += 3) {
            logger.info("Writing to address: " + command[i]);
            i2c.write(command[i], new byte[]{command[i + 1], command[i + 2]}, 2);
        }
    }

    public static void main(String[] args) {
        logger.info("Starting Alicorn Client System");

        // Prepare Display Color.
        transform3xWrite(DisplayTools.commandForColor(100, 50, 150));

        // Setup text information.
        transform3xWrite(DisplayTools.commandForText("Sup Fam"));

        while (true) {
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
