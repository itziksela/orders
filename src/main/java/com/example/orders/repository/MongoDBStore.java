package com.example.orders.repository;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.ConnectionString;
import java.util.ArrayList;

import com.example.orders.dto.*;

public class MongoDBStore implements IRepository<BaseData> {
    @Autowired
    private MongoDatabase db;

    public void connect() {

        String host = System.getenv().getOrDefault("MONGODB_SERVICE_SERVICE_HOST", "localhost");
        long port = 27017;
        var username = "username";
        var password = "password";

        StringBuilder connectionString = new StringBuilder(String.format("mongodb://%s:%d", host, port));
        if (!host.equals("localhost")) {
            connectionString = new StringBuilder(String.format("mongodb://%s:%s@:%s:%d", username, password, host, port));
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
    public List<BaseData> getAll(String repositoryName) {
        MongoCollection<BaseData> collection = db.getCollection(repositoryName, BaseData.class);
        List<BaseData> o = collection.find().into(new ArrayList<BaseData>());
        return o;
    }
}
