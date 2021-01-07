package com.example.orders;

class OrderDetails {
    private String id;
    private String details;

    public OrderDetails(){
        
    }
    public OrderDetails(String details){
        this.details = details;
    }

    public OrderDetails(String id, String details){
        this.details = details;
        this.id = details;
    }

    @Override
    public String toString() {
        return details;
    }
}