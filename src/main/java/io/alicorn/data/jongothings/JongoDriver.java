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

    public MongoCollection getCollection(String collectionName) {
        return jongo.getCollection(collectionName);
    }

    public Query createQuery(String query, Object... params) {
        return jongo.createQuery(query, params);
    }

    public Command runCommand(String query) {
        return jongo.runCommand(query);
    }

    public Command runCommand(String query, Object... params) {
        return jongo.runCommand(query, params);
    }
}
