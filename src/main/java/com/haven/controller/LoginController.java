package com.haven.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.haven.postgress.model.Customers;
import com.haven.postgress.model.LoginRequest;
import com.haven.postgress.service.CustomerService;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    CustomerService customerService;
    
    @CachePut(value = "userCache", key = "#customer.customer_id")
    public Customers cacheAuthenticatedCustomer(Customers customer) {
        return customer;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        // Perform authentication logic in the service
        Optional<Customers> authenticatedCustomer = customerService.authenticateCustomer(loginRequest);

        if (authenticatedCustomer.isPresent()) {
            Customers user = authenticatedCustomer.get();

            // Store the authenticated customer in Redis with the key as customer_id
            cacheAuthenticatedCustomer(user);

            return ResponseEntity.ok("Login successful");
        } else {
            // If authentication fails, return an unauthorized response
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }

//    @GetMapping("/getUserById/{id}")
//    public ResponseEntity<?> getUserById(@PathVariable String id, HttpServletRequest httpServletRequest) {
//        ValueOperations<String, Customers> valueOperations = redisTemplate.opsForValue();
//
//        if (valueOperations.get(id) != null) {
//            Customers cachedCustomer = valueOperations.get(id);
//            return ResponseEntity.ok().body(cachedCustomer);
//        } else {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Key not found in Redis");
//        }
//    }
}
