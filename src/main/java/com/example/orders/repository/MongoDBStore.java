package com.example.orders.repository;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.ConnectionString;
import java.util.ArrayList;
import com.example.orders.data.*;

public class MongoDBStore<T extends BaseData> implements IRepository<T> {
    @Autowired
    private MongoDatabase db;
    private String collectionName;
    private T data;
    public MongoDBStore(T data) {
        collectionName = data.getClass().getSimpleName();
        this.data = data;

    }

    public void connect() {

        String host = System.getenv().getOrDefault("MONGODB_SERVICE_SERVICE_HOST", "localhost");
        long port = 27017;
        var username = "username";
        var password = "password";

        StringBuilder connectionString = new StringBuilder(String.format("mongodb://%s:%d", host, port));
        if (!host.equals("localhost")) {
            connectionString = new StringBuilder(
                    String.format("mongodb://%s:%s@%s:%d", username, password, host, port));
        }

        ConnectionString uri = new ConnectionString(connectionString.toString());
        MongoService service = new MongoService(uri, "orders");
        db = service.GetActiveDatabase();
    }

    @Override
    public String saveSingleItem(BaseData data) {
        if (data != null) {
            MongoCollection<BaseData> collection = db.getCollection(data.CollectionName(), BaseData.class);
            collection.insertOne(data);
            var id = data.getId();
            return id;
        }
        return "nothing to save";
    }

    @Override
    public <V extends BaseData> List<V> getAll() {
        MongoCollection<V> collection = (MongoCollection<V>) db.getCollection(collectionName, data.getClass());
        return collection.find().into(new ArrayList<V>());
    }
}
