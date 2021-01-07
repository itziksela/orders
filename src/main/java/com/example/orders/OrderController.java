package com.example.orders;

import com.example.orders.repository.*;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;

@RestController
class OrdersController {
    private String version = "1.0.8";

    private String dbType = "local";
    private IStrategy storage;

    public OrdersController() {
        if (dbType.equals("local")) {
            storage = new LocalStore();
        }
        else if (dbType.equals("mongo")) {
            storage = new MondoDBStore();
        }
    }


    @GetMapping("/")
    public String welcome(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format("Hello %s, welcom to Aharon & Gil successful restaurant! %s", name, LocalDateTime.now());
    }

    @GetMapping("/getversion")
    public String getVersion() {
        return String.format("Version %s", version);
    }

    @GetMapping("/getorders")
    public String getAllOrders() {
        storage.getOrders();
        return String.format("get order %s!", LocalDateTime.now());
    }

    @GetMapping("/saveorder")
    public String saveOrder(@RequestParam String details) {
        String orderId = storage.saveSingleOrder(details);
        return String.format("order saved %s! %s", details, orderId);
    }

    @GetMapping("/updateorder")
    public String updateOrder(@RequestParam String id, @RequestParam String details) {
        storage.saveSingleOrder(id, details);
        return String.format("order saved %s! %s", details, LocalDateTime.now());
    }
}