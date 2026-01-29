package com.practice.order;

import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final List<Map<String, Object>> orders = new ArrayList<>();

    @PostMapping("/create")
    public Map<String, Object> createOrder(@RequestBody Map<String, Object> order) {
        orders.add(order);
        return Map.of("status", "success", "order", order);
    }

    @GetMapping("/all")
    public List<Map<String, Object>> getAllOrders() {
        return orders;
    }
}
