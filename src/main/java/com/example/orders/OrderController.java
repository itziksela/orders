package com.example.orders;

import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;

@RestController
class OrdersController {

    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {

        return String.format("Hello %s! %s", name, LocalDateTime.now());
    }

    @GetMapping("/getorders")
    public String getAllOrders() {
        getOrders();
        return String.format("get order %s!", LocalDateTime.now());
    }

    @GetMapping("/saveorder")
    public String saveOrder(@RequestParam String details) {
        saveSingleOrder(details);
        return String.format("order saved %s! %s", details, LocalDateTime.now());
    }

    private void getOrders() {
        //TO DO
    }


    private void saveSingleOrder(String details) {
        if (details != null) {
            OrderDetails o = new OrderDetails(details);
        }
    } 
}


