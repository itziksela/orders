package com.example.orders.repository;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.ConnectionString;

import static com.mongodb.client.model.Updates.*;
import java.util.ArrayList;
import com.example.orders.data.*;

public class MongoDBStore<T extends BaseData> implements IRepository<T> {
    @Autowired
    private MongoDatabase db;
    private T data;

    public MongoDBStore(T data) {
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
    public String saveSingleItem(T data) {
        if (data != null) {
            MongoCollection<T> collection = (MongoCollection<T>) db.getCollection(data.CollectionName(),
                    data.getClass());
            collection.insertOne(data);
            return data.getId();
        }
        return "nothing to save";
    }

    @Override
    public List<T> getAll() {
        MongoCollection<T> collection = (MongoCollection<T>) db.getCollection(data.CollectionName(), data.getClass());
        return collection.find().into(new ArrayList<T>());
    }

    public List<T> getPendingOrders() {
        MongoCollection<T> collection = (MongoCollection<T>) db.getCollection(data.CollectionName(), data.getClass());
        return collection.find(Filters.eq("orderStatus", OrderStatus.PENDING.toString())).into(new ArrayList<T>());
    }

    public void updateOrderStatus(String id, String status) {
        MongoCollection<OrderDetails> collection = db.getCollection(data.CollectionName(), OrderDetails.class);
        collection.updateOne(Filters.eq("_id", id), set("OrderStatus", OrderStatus.INPROGRESS.toString()));
    }
}
