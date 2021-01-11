package com.example.orders.data;

import org.springframework.data.annotation.Id;
import org.bson.types.ObjectId;

public class BaseData {
    @Id
    protected String id;

    public BaseData() {
        this.id = new ObjectId().toString();
    }

    public String getId() {
        return this.id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String CollectionName() {
        return this.getClass().getSimpleName();
    }
}
