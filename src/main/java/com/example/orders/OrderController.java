package com.example.orders;

import com.example.orders.repository.*;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import com.example.orders.data.*;
import java.util.List;

@RestController
class OrdersController {
    private String version = "1.0.26";
    private static final Logger LOGGER = LoggerFactory.getLogger(OrdersController.class);

    private MongoDBStore<OrderDetails> getStorage() {
        var storage = new MongoDBStore(new OrderDetails());
        storage.connect();
        return storage;
    }

    @GetMapping("/")
    public String welcome(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format("Hello %s, welcom to Aharon & Gil successful restaurant! %s", name, LocalDateTime.now());
    }

    @GetMapping("/getversion")
    public String getVersion() {
        return String.format("Version %s", version);
    }

    @GetMapping("/getinfo")
    public String getServerName() {
        String host = System.getenv().getOrDefault("MONGODB_SERVICE_SERVICE_HOST", "localhost");
        String username = System.getenv().getOrDefault("MONGO_USERNAME", "");
        String password = System.getenv().getOrDefault("MONGO_PASSWORD", "");
        return String.format("get db %s %s/%s", host, username, password);
    }

    @GetMapping("/getorders")
    public String getAllOrders() {
        var storage = getStorage();
        List<OrderDetails> orders = storage.getAll();
        StringBuilder data = new StringBuilder("Order Details\n");
        for (OrderDetails order : orders) {
            data.append(String.format("order: %s%n",  order.toString()));
        }
        return data.toString();
    }

    @GetMapping("/saveorder")
    public String saveOrder(@RequestParam String details) {
        OrderDetails orderDetails = new OrderDetails(details);

        var storage = getStorage();
        String orderId = storage.saveSingleItem(orderDetails);
        return String.format("order saved %s! %s", details, orderId);
    }

    @GetMapping("/updateorderstatus")
    public String updateOrderStatus(@RequestParam String id, @RequestParam String status) {
        try {
            var storage = getStorage();
            storage.updateOrderStatus(id, status);
            return "Success";
        } catch (Exception e) {
            return String.format("Internal server error: %s %s", e.getMessage(), e.getStackTrace());
        }
    }

    @GetMapping("/getpendingorders")
    public String getPendingOrders() {
        try {
            var storage = getStorage();
            List<OrderDetails> orders = storage.getPendingOrders();
            StringBuilder data = new StringBuilder("Order Details\n");
            for (OrderDetails order : orders) {
                data.append(String.format("order: %s%n",  order.toString()));
            }
            return data.toString();
        } catch (Exception e) {
           return String.format("Internal server error: %s %s", e.getMessage(), e.getStackTrace());
        }
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public final Exception handleAllExceptions(RuntimeException e) {
        LOGGER.error("Internal server error.", e);
        return e;
    }
}