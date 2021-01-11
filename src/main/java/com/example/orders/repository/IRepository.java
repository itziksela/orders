package com.example.orders.repository;

import java.util.List;
import com.example.orders.dto.*;

public interface IRepository<T> {

    String saveSingleItem(T data);

    public List<BaseData> getAll(String repositoryName);

    void connect();
}