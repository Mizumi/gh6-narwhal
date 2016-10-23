# Project Narwhal #
The Narwhal Project is a combination web application and Internet of Things integration that allows homeless shelters to both share data amongst themselves, and quickly get information out to homeless across a dense metropolitan region.

## Sharing data between homeless services ##
Our system boasts a typical web application and dashboard that allows two kinds of individuals, ***agents*** (authorized members of homeless facilities and continuums of care) and ***clients*** (homeless), the ability to quickly record information, share it between shelters or services and disseminate real-time information to all users of the system, including info regarding available beds, meal times, emergency updates and more.

## Getting information to the homeless ##
Project Narwhal wouldn't be named after our favorite animal, however, if it didn't boast a dash of IoT-enabled features. During the past weekend, we hacked together a simple proof-of-concept IoT device that connects to the central Project Narwhal server and displays real-time information about the kind and location of homeless services currently available for a given continuum of care region. These devices are relatively low-cost and could be deployed in the downtown area at key locations, providing homeless with visibility on information that they would otherwise have to use a cell phone or go to their nearest center to learn about.

## Our Technology ##
This project uses a variety of fun and interesting technologies to get the job done, including:

- A central MongoDB server for persisting all information for the Narwhal system.
- A customized, easy to deploy Java web service that has no dependencies and is rolled into a single JAR.
- To achieve this, we leveraged a combination of Java 8, the Spark micro web-framework and Jackson.
- An AngularJS/HTML5 front-end built with Webpack and served by our Java web service during production.
- A Narwhal Client application written in Java and (very slightly only for JNI bridging) in C that drives a Raspberry Pi with a GrovePi development board and an LCD/RGB display.

In a production set up of this application, we would imagine that the display used with the Pi unit would be significantly larger in order to accommodate more information and provide better visibility in a metropolitan environment. 

## IoT Security ##
One thing we know will concern people is the security of IoT applications and clients. As a result, our IoT implementation for this hackathon makes use of a public REST service that contains only publicly available information which our devices can connect to without authorization; it would still theoretically be possible to physically hack one of the IoT devices to do something malicious, but we felt that securing against that avenue was far outside the scope of a proof-of-concept system this weekend. And besides, what are you going to do with a device that doesn't perform any private data gathering?

## Building and Running the App ##
To build and run the project, clone it down to your system and follow these steps:

- Navigate to `device-server/src/main/webapp`
- Invoke `npm install`
- Invoke `npm install -g webpack`
- Invoke `npm run build`
- This will setup and build the webapp to `device-server/src/main/webapp/dist`
- Navigate to the root folder
- Invoke `mvn clean install`
- This will build both the Java web service (in `device-server/target/gh6.jar`) and the IoT client (in `device-client/target/AlicornDeviceClient.jar`).
- Deploy the device client jar to your Raspberry Pi (please note the Pi must have a Grove Pi+ board installed and configured, along with a Grove LCD RGB Display attached).
- Run the web server from the root directory of the project by invoking `java -jar device-server/target/gh6.jar`.

## Legal ##
This project is licensed underneath the Apache License V2; please find a brief copy and more details within our LICENSE.txt.

***The Alicorn Logo is Copyright (c) 2015 - 2016 Brandon Sanders, All Rights Reserved.***
***The Alicorn Logo may not be used, in any form, without prior written permission from Brandon Sanders.***