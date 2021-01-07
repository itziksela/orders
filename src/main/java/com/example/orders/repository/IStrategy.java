package com.example.orders.repository;

public interface IStrategy {
    String saveSingleOrder(String details);
    String saveSingleOrder(String id, String details);
    void getOrders();
}