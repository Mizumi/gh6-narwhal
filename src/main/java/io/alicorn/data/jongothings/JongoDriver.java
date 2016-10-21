package io.alicorn.data.jongothings;

import com.mongodb.MongoClient;
import org.jongo.Jongo;
import org.jongo.MongoCollection;

public class JongoDriver {
    private static Jongo jongo;
    static {
        jongo = new Jongo(new MongoClient().getDB("GH6"));
    }

    public MongoCollection getCollection(String collectionName) {
        return jongo.getCollection(collectionName);
    }
}
