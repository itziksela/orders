
package com.example.orders.repository;

import java.util.List;
import com.example.orders.OrderDetails;
import java.util.ArrayList; 

public class LocalStore implements IRepository {
    private List<OrderDetails> orderDetails;

    @Override
    public String saveSingleOrder(String details) {
        return "nothing to save";
    }

    @Override
    public String saveSingleOrder(String id, String details) {
        return "not exist";
    }

    @Override
    public List<OrderDetails> getOrders() {
        List<OrderDetails> data = new ArrayList<OrderDetails>();
        return data;
    }

    @Override
    public void connect() {
        //
    }
}
