package com.haven.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;

import com.haven.postgress.dao.OrdersRepository;
import com.haven.postgress.model.Orders;
import com.haven.postgress.service.OrderService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class OrderServiceTest {

    @Mock
    private OrdersRepository ordersRepository;

    @InjectMocks
    private OrderService orderService;

    @MockBean
    private CacheManager cacheManager;

    @Test
    public void testGetAllOrders() {
        // Mocking the behavior of the repository
        Orders order1 = new Orders(1, "2", "2024-01-2 23:16:16", "processed");
        Orders order2 = new Orders(2, "2", "2024-01-2 23:16:16", "processed");
        when(ordersRepository.findAll()).thenReturn(Arrays.asList(order1, order2));

        // Configure a simple cache manager for testing
        CacheManager simpleCacheManager = new ConcurrentMapCacheManager("ordersList");
        when(cacheManager.getCache("ordersList")).thenReturn(new ConcurrentMapCache("ordersList"));

        // First call to getAllOrders - DB should be called
        List<Orders> result1 = orderService.getAllOrders();

        // Second call to getAllOrders - DB should not be called, and data should be fetched from cache
        List<Orders> result2 = orderService.getAllOrders();

        // Assertions
        assertThat(result1).containsExactly(order1, order2);
        assertThat(result2).containsExactly(order1, order2);
    }

    @Test
    public void testGetOrdersByCustomerId() {
        // Mocking the behavior of the repository
        Orders order1 = new Orders(1, "2", "2024-01-2 23:16:16", "processed");
        Orders order2 = new Orders(2, "2", "2024-01-2 23:16:16", "processed");
        when(ordersRepository.findByCustomer_id("2")).thenReturn(Arrays.asList(order1, order2));

        // Configure a simple cache manager for testing
        CacheManager simpleCacheManager = new ConcurrentMapCacheManager("orderList");
        when(cacheManager.getCache("orderList")).thenReturn(new ConcurrentMapCache("orderList"));

        // First call to getOrdersByCustomerId - DB should be called
        List<Orders> result1 = orderService.getOrdersByCustomerId("2");

        // Second call to getOrdersByCustomerId - DB should not be called, and data should be fetched from cache
        List<Orders> result2 = orderService.getOrdersByCustomerId("2");

        // Assertions
        assertThat(result1).containsExactly(order1, order2);
        assertThat(result2).containsExactly(order1, order2);
    }

    @Test
    public void testGetOrderById() {
        // Mocking the behavior of the repository
        Orders order = new Orders(1, "2", "2024-01-2 23:16:16", "processed");
        when(ordersRepository.findById(1)).thenReturn(Optional.of(order));

        // Configure a simple cache manager for testing
        CacheManager simpleCacheManager = new ConcurrentMapCacheManager("order");
        when(cacheManager.getCache("order")).thenReturn(new ConcurrentMapCache("order"));

        // First call to getOrderById - DB should be called
        Optional<Orders> result1 = orderService.getOrderById(1);       

        // Second call to getOrderById - DB should not be called, and data should be fetched from cache
        Optional<Orders> result2 = orderService.getOrderById(1);

        // Assertions
        assertThat(result1).isEqualTo(Optional.of(order));
        assertThat(result2).isEqualTo(Optional.of(order));
    }
}

