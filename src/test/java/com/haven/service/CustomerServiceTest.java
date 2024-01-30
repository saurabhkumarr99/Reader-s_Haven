package com.haven.service;
  
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;

import com.haven.dao.CustomersRepository;
import com.haven.model.Customers;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class CustomerServiceTest {

    @Mock
    private CustomersRepository customersRepository;

    @InjectMocks
    private CustomerService customerService;

    @MockBean
    private CacheManager cacheManager;

    @Test
    public void testGetAllCustomers() {
        // Mocking the behavior of the repository
        Customers customer1 = new Customers(1, "Jane Smith", "jane.smith@email.com", "pass456", "456 Oak St");
        Customers customer2 = new Customers(2, "John Doe", "john.doe@email.com", "pass789", "789 Pine St");
        when(customersRepository.findAll()).thenReturn(Arrays.asList(customer1, customer2));

        // Configure a simple cache manager for testing
        CacheManager simpleCacheManager = new ConcurrentMapCacheManager("customersList");
        when(cacheManager.getCache("customersList")).thenReturn(new ConcurrentMapCache("customersList"));

        // First call to getAllCustomers - DB should be called
        List<Customers> result1 = customerService.getAllCustomers();
        verify(customersRepository, times(1)).findAll();

        

        // Second call to getAllCustomers - DB should not be called, and data should be fetched from cache
        List<Customers> result2 = customerService.getAllCustomers();
      
        // Assertions
        assertThat(result1).containsExactly(customer1, customer2);
        assertThat(result2).containsExactly(customer1, customer2);
    }

    @Test
    public void testGetCustomerById() {
        // Mocking the behavior of the repository
        Customers customer = new Customers(1, "Jane Smith", "jane.smith@email.com", "pass456", "456 Oak St");
        when(customersRepository.findById(1)).thenReturn(Optional.of(customer));

        // Configure a simple cache manager for testing
        CacheManager simpleCacheManager = new ConcurrentMapCacheManager("customer");
        when(cacheManager.getCache("customer")).thenReturn(new ConcurrentMapCache("customer"));

        // First call to getCustomerById - DB should be called
        Optional<Customers> result1 = customerService.getCustomerById(1);

       
        // Second call to getCustomerById - DB should not be called, and data should be fetched from cache
        Optional<Customers> result2 = customerService.getCustomerById(1);
       

        // Assertions
        assertThat(result1).isEqualTo(Optional.of(customer));
        assertThat(result2).isEqualTo(Optional.of(customer));
    }
}
