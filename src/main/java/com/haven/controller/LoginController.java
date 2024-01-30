package com.haven.controller;
//
//import java.time.LocalDateTime;
//import java.util.Optional;
//import java.util.concurrent.TimeUnit;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.haven.model.Customers;
//import com.haven.model.LoginRequest;
//import com.haven.model.UserSession;
//import com.haven.service.CustomerService;
//
//import jakarta.servlet.http.HttpServletRequest;
//
//@RestController
//@RequestMapping("/api")
//public class LoginController {
//
//	@Autowired
//	CustomerService customerService;
//
////	@Autowired
////	private RedisTemplate<String, UserSession> redisTemplate;
//
//	@Value("${session.timeout.seconds}")
//	private long sessionTimeoutSeconds;
//
//	@PostMapping("/login")
//	public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
//
//		// Perform authentication logic in the service
//		Optional<Customers> authenticatedCustomer = customerService.authenticateCustomer(loginRequest);
//
//		if (authenticatedCustomer.isPresent()) {
//
//			Customers user = authenticatedCustomer.get();
//
//			// Create a UserSession and store it in Redis with expiration time
//			UserSession userSession = new UserSession(String.valueOf(user.getCustomer_id()), LocalDateTime.now());
//			System.out.println(userSession);
//			//redisTemplate.opsForValue().set(String.valueOf(user.getCustomer_id()), userSession, sessionTimeoutSeconds,
//			//		TimeUnit.SECONDS);
//
//			return ResponseEntity.ok("Login successful");
//		} else {
//			// If authentication fails, return an unauthorized response
//			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
//		}
//	}
//
//	@GetMapping("/getUserById/{id}")
//	public ResponseEntity<?> getUserById(@PathVariable String id, HttpServletRequest httpServletRequest) {
//
//		UserSession userSession =null;
//		//UserSession userSession = redisTemplate.opsForValue().get(id);
//
//		return ResponseEntity.ok().body(userSession);
//	}
//}

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.haven.model.Customers;
import com.haven.model.LoginRequest;
import com.haven.model.UserSession;
import com.haven.service.CustomerService;
import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    CustomerService customerService;

    private final CacheManager cacheManager;

    @Value("${session.timeout.seconds}")
    private long sessionTimeoutSeconds;

    public LoginController() {
        this.cacheManager = cacheManager();
    }

    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager("userCache");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {

        // Perform authentication logic in the service
        Optional<Customers> authenticatedCustomer = customerService.authenticateCustomer(loginRequest);

        if (authenticatedCustomer.isPresent()) {

            Customers user = authenticatedCustomer.get();

            // Create a UserSession and store it in the cache with expiration time
            UserSession userSession = new UserSession(String.valueOf(user.getCustomer_id()), LocalDateTime.now());
            Cache userCache = cacheManager.getCache("userCache");
            if (userCache != null) {
                userCache.put(String.valueOf(user.getCustomer_id()), userSession);
                return ResponseEntity.ok("Login successful");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Cache configuration error");
            }
        } else {
            // If authentication fails, return an unauthorized response
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }

    @GetMapping("/getUserById/{id}")
    public ResponseEntity<?> getUserById(@PathVariable String id, HttpServletRequest httpServletRequest) {
        Cache userCache = cacheManager.getCache("userCache");

        if (userCache != null) {
            UserSession userSession = userCache.get(id, UserSession.class);
            return ResponseEntity.ok().body(userSession);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Cache configuration error");
        }
    }
}

