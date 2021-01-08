package com.example.orders.repository;

import java.util.List;
public interface IRepository<T> {
    String saveSingleOrder(String details);
    String saveSingleOrder(String id, String details);
    List<T> getOrders();
    void connect();
}