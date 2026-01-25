package com.practice;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RestController
public class OrderApp {
    public static void main(String[] args) { SpringApplication.run(OrderApp.class, args); }
    private final RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/place-order/{id}")
    public String placeOrder(@PathVariable String id) {
        String product = restTemplate.getForObject("http://localhost:8081/product/" + id, String.class);
        String stock = restTemplate.getForObject("http://localhost:8082/stock/" + id, String.class);
        return "Order confirmed for " + product + ". Status: " + stock;
    }
}
