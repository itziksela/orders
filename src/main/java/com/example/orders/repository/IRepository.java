package com.example.orders.repository;

import java.util.List;
import com.example.orders.data.*;

public interface IRepository<T extends BaseData> {

    String saveSingleItem(T data);

    public <V extends BaseData> List<V> getAll();

    void connect();
}