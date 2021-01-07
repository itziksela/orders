package com.example.orders.repository;

public interface IRepository {
    String saveSingleOrder(String details);
    String saveSingleOrder(String id, String details);
    void getOrders();
}