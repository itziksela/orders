package com.example.orders.data;

import org.bson.codecs.pojo.annotations.BsonProperty;
import java.time.LocalDate;

public class OrderDetails extends BaseData {
    public OrderDetails() {
        this.orderDate = LocalDate.now();
        this.orderStatus = OrderStatus.PENDING;
    }

    public OrderDetails(String details) {
        this.details = details;
        this.orderDate = LocalDate.now();
        this.orderStatus = OrderStatus.PENDING;
    }

    public OrderDetails(String id, String details) {
        this.details = details;
        this.id = id;
    }

    @BsonProperty(value = "details")
    private String details;

    private LocalDate orderDate;
    private OrderStatus orderStatus;

    @Override
    public String toString() {
        return String.format("%s %s, %s, %s", this.id, this.details, this.orderDate.toString(), this.orderStatus.toString());
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(final String details) {
        this.details = details;
    }

    public LocalDate getOrderDate() {
        return this.orderDate;
    }

    public void setOrderDate(final LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(final OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
}