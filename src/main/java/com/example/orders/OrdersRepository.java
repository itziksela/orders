package com.example.orders;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrdersRepository extends MongoRepository<OrderDetails, String> {

}