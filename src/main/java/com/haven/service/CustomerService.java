package com.haven.service;

import com.haven.dao.CustomersRepository;
import com.haven.model.Customers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomersRepository customersRepository;

    // Create a customer
    public Customers createCustomer(Customers customer) {
        return customersRepository.save(customer);
    }

    // Retrieve all customers
    public List<Customers> getAllCustomers() {
        return customersRepository.findAll();
    }

    // Retrieve a customer by ID
    public Optional<Customers> getCustomerById(int id) {
        return customersRepository.findById(id);
    }

    // Update a customer
    public Customers updateCustomer(int id, Customers updatedCustomer) {
        Optional<Customers> optionalCustomer = customersRepository.findById(id);

        if (optionalCustomer.isPresent()) {
            Customers existingCustomer = optionalCustomer.get();
            existingCustomer.setName(updatedCustomer.getName());
            existingCustomer.setEmail(updatedCustomer.getEmail());
            existingCustomer.setPassword(updatedCustomer.getPassword());
            existingCustomer.setAddress(updatedCustomer.getAddress());
            // Set other properties as needed

            return customersRepository.save(existingCustomer);
        } else {
            // Handle the case where the customer with the given ID is not found
            return null;
        }
    }

    // Delete a customer by ID
    public void deleteCustomer(int id) {
        customersRepository.deleteById(id);
    }
}
