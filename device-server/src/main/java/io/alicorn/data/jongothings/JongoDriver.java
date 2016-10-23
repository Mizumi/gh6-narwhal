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
package io.alicorn.data.jongothings;

import com.mongodb.MongoClient;
import org.jongo.Command;
import org.jongo.Jongo;
import org.jongo.MongoCollection;
import org.jongo.query.Query;

public class JongoDriver {
    private static Jongo jongo;
    static {
        jongo = new Jongo(new MongoClient().getDB("GH6"));
    }

    public static MongoCollection getCollection(String collectionName) {
        return jongo.getCollection(collectionName);
    }

    public static Query createQuery(String query, Object... params) {
        return jongo.createQuery(query, params);
    }

    public static Command runCommand(String query) {
        return jongo.runCommand(query);
    }

    public static Command runCommand(String query, Object... params) {
        return jongo.runCommand(query, params);
    }
}
