package com.practice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RestController
public class OrderApp {

    public static void main(String[] args) {
        SpringApplication.run(OrderApp.class, args);
    }

    // This Bean tells Spring how to create the RestTemplate
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    // These values can be overridden in application.properties or via command line
    @Value("${catalog.url:http://localhost:8081}")
    private String catalogUrl;

    @Value("${inventory.url:http://localhost:8082}")
    private String inventoryUrl;

    @GetMapping("/place-order/{id}")
    public String placeOrder(@PathVariable String id) {
        try {
            // Calling Catalog Service
            String product = restTemplate().getForObject(catalogUrl + "/product/" + id, String.class);
            
            // Calling Inventory Service
            String stock = restTemplate().getForObject(inventoryUrl + "/stock/" + id, String.class);
            
            return "Order confirmed for: " + product + " | Status: " + stock;
        } catch (Exception e) {
            return "Error: Could not connect to internal services. Check if Catalog and Inventory are running!";
        }
    }
}
