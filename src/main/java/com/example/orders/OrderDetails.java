package com.example.orders;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

@Document(collection = "orders")
class OrderDetails {

    @Id
    private String id;
    private String details;

    public OrderDetails(){

    }
    public OrderDetails(String details){
        this.details = details;
        //this.id = java.util.UUID.randomUUID().toString();
    }

    public OrderDetails(String id, String details){
        this.details = details;
        this.id = id;
    }

    @Override
    public String toString() {
        return details + " " + id;
    }

    public String getId()
    {
        return this.id;
    }
}