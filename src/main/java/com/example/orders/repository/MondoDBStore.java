
package com.example.orders.repository;

import com.example.orders.*;
import com.example.orders.OrderDetails;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.ConnectionString;
import java.util.ArrayList;

public class MondoDBStore implements IRepository<OrderDetails> {
    @Autowired
    private MongoDatabase db;
    private static final String COLLECTION_NAME = "OrderDetails";

    public MondoDBStore() {
        connect();
    }

    public void connect() {
        StringBuilder connectionString = new StringBuilder(String.format("mongodb://%s:%d", "localhost", 27017));
        ConnectionString uri = new ConnectionString(connectionString.toString());
        MongoService service = new MongoService(uri, "orders");
        db = service.GetActiveDatabase();
    }

    @Override
    public String saveSingleOrder(String details) {
        if (details != null) {
            OrderDetails o = new OrderDetails(details);
            MongoCollection<OrderDetails> collection = db.getCollection(COLLECTION_NAME, OrderDetails.class);
            collection.insertOne(o);
            return o.getId();
        }
        return "nothing to save";
    }

    @Override
    public String saveSingleOrder(String id, String details) {
        if (id != null) {
            OrderDetails o = new OrderDetails(details);
            return o.getId();
        }
        return "not exist";
    }

    @Override
    public List<OrderDetails> getOrders() {
        MongoCollection<OrderDetails> collection = db.getCollection(COLLECTION_NAME, OrderDetails.class);
        List<OrderDetails> o = collection.find().into(new ArrayList<OrderDetails>());
        return o;
    }
}
