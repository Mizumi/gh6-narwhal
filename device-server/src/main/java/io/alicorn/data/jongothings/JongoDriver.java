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
