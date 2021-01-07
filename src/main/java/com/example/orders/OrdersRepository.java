package com.example.orders;

import org.springframework.data.mongodb.repository.MongoRepository;

interface OrdersRepository extends MongoRepository<OrderDetails, String> {

}