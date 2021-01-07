
package com.example.orders.repository;

public class LocalStore implements IStrategy {

    @Override
    public String saveSingleOrder(String details) {
        return "nothing to save";
    }   

    @Override
    public String saveSingleOrder(String id, String details) {
        return "not exist";
    } 

    @Override
    public void getOrders() {
    }
}
