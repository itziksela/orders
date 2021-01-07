package com.example.orders;

import org.springframework.boot.web.client.RootUriTemplateHandler;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;

@RestController
class OrdersController {

    @GetMapping("/")
    public String welcome(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format("Hello %s, welcom to Aharon & Gil successful restaurant! %s", name, LocalDateTime.now());
    }

    @GetMapping("/getorders")
    public String getAllOrders() {
        getOrders();
        return String.format("get order %s!", LocalDateTime.now());
    }

    @GetMapping("/saveorder")
    public String saveOrder(@RequestParam String details) {
        String orderId = saveSingleOrder(details);
        return String.format("order saved %s! %s", details, orderId);
    }

    @GetMapping("/updateorder")
    public String updateOrder(@RequestParam String id, @RequestParam String details) {
        saveSingleOrder(id, details);
        return String.format("order saved %s! %s", details, LocalDateTime.now());
    }

    private void getOrders() {
        //TO DO
    }


    private String saveSingleOrder(String details) {
        if (details != null) {
            OrderDetails o = new OrderDetails(details);
            return o.getId(); 
        }
        return "nothing to save";
    } 

    private String saveSingleOrder(String id, String details) {
        if (id != null) {
            OrderDetails o = new OrderDetails(id, details);
            return o.getId(); 
        }
        return "not exist";
    } 
}


