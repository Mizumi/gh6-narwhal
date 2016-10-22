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
package io.alicorn.server.http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Filter;
import spark.Route;
import spark.Spark;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.File;

/**
 * TODO:
 *
 * @author Brandon Sanders [brandon@alicorn.io]
 */
@Singleton
public class SparkWrapper {
//Private//////////////////////////////////////////////////////////////////////

    private static final Logger logger = LoggerFactory.getLogger(SparkWrapper.class);

//Protected////////////////////////////////////////////////////////////////////

//Public///////////////////////////////////////////////////////////////////////

    @Inject
    public SparkWrapper() {
        Spark.port(9789);
        Spark.externalStaticFileLocation("src/main/webapp/dist");
        logger.info("Spark Wrapper started.");
    }

    public void before(Filter filter) {
        Spark.before(filter);
    }

    public void after(Filter filter) {
        Spark.after(filter);
    }

    public void post(String path, Route route) {
        Spark.post(path, route);
    }

    public void get(String path, Route route) {
        Spark.get(path, route);
    }
}
