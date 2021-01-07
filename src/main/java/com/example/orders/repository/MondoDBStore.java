
package com.example.orders.repository;
import com.example.orders.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Collections;

public class MondoDBStore implements IRepository {

    @Autowired
    private OrdersRepository ordersRepository;

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
