package com.example.orders;

import com.example.orders.repository.*;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

@RestController
class OrdersController {
    private String version = "1.0.11";
    private final static Logger LOGGER = LoggerFactory.getLogger(OrdersController.class);

    private String dbType = "mongo";
    private IRepository storage;

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
        try {
            storage.connect();
        } catch (Exception e) {
            LOGGER.error("Internal server error.", e);
        }

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

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public final Exception handleAllExceptions(RuntimeException e) {
        LOGGER.error("Internal server error.", e);
        return e;
    }
}