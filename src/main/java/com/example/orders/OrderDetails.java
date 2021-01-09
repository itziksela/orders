package com.example.orders;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

@Document(collection = "orders")
public class OrderDetails {

    @Id
    private String id;

    @BsonProperty(value = "details")
    private String details;

    public OrderDetails() {
        this.id = new ObjectId().toString();
    }

    public OrderDetails(String details) {
        this.details = details;
        this.id = new ObjectId().toString();
    }

    public OrderDetails(String id, String details) {
        this.details = details;
        this.id = id;
    }

    @Override
    public String toString() {
        return details + " " + id;
    }

    public String getId() {        
        return this.id.toString();
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(final String details) {
        this.details = details;
    }
}