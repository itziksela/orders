
package com.example.orders.repository;

import com.example.orders.*;
import com.example.orders.OrderDetails;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Collections;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.MongoClientURI;
import org.springframework.beans.factory.annotation.*;

public class MondoDBStore implements IRepository {
    @Autowired
    private OrdersRepository ordersRepository;

    @Value("${spring.data.mongodb.uri}")
    private String connectionString;

    public MondoDBStore() {

    }

    @Override
    public String saveSingleOrder(String details) {
        if (details != null) {
            OrderDetails o = new OrderDetails(details);
            ordersRepository.save(o);
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
    public void getOrders() {
        List<OrderDetails> orders = ordersRepository.findAll();
        Collections.reverse(orders);
    }
}
