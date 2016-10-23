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

    public static void transform3xWrite(byte[] command) {
        assert command.length % 3 == 0;
        for (int i = 0; i < command.length; i += 3) {
            i2c.write(command[i], new byte[]{command[i + 1], command[i + 2]}, 2);
        }
    }

    public static void main(String[] args) {
        logger.info("Starting Alicorn Client System");

        // Write text to the LCD display.
        transform3xWrite(DisplayTools.commandForColor(100, 50, 150));
        transform3xWrite(DisplayTools.commandForText("Sup Fam"));

        while (true) {
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

//        logger.info("Starting Wii Nunchuck example application.");
//
//        // Write 0x40 and 0x00 to initialize the nunchuck.
//        logger.info("Initializing Nunchuck.");
//        i2c.write(NUNCHUCK_ADDR, new byte[]{(byte)0x40, (byte) 0x00},2);
//        logger.info("Nunchuck initialized.");
//
//        // Loop forever, reading from the nunchuck.
//        System.out.println("#### Wii Nunchuck Tracking Data ####");
//        System.out.println("");
//        byte[] response;
//        while (true) {
//            // Write 0x00 to the nunchuck to request data.
//            i2c.write(NUNCHUCK_ADDR, new byte[]{(byte) 0x00},1);
//
//            // Read the 6-byte response from the nunchuck.
//            response = i2c.read(NUNCHUCK_ADDR, new byte[6], 6);
//
//            // Decode response by XOR 0x17 and add 0x17.
//            for (int i = 0; i < response.length; i++) {
//                response[i] = (byte) ((response[i] ^ 0x17) + 0x17);
//            }
//
//            // Clear the most recent line so that we don't spew out tons of lines of content.
//            System.out.print("\r");
//            for (int i = 0; i < 100; i++) System.out.print(" ");
//            System.out.print("\r");
//
//            // Print out tracking data.
//            System.out.print(String.format("Stick (X %d Y %d) | Gyro (X %d Y %d Z %d) | Buttons (%d)",
//                                           response[0], response[1], response[2], response[3],
//                                           response[4], response[5]));
//
//            // Sleep for a bit.
//            try {
//                Thread.sleep(250);
//            } catch (Exception e) {
//                logger.error(e.getMessage(), e);
//            }
//        }
    }
}
