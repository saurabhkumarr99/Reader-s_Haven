package com.haven.postgress.service;

import com.haven.postgress.dao.CustomersRepository;
import com.haven.postgress.model.Customers;
import com.haven.postgress.model.LoginRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
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
	@Cacheable(key = "'allCustomers'", value = "customersList")
	public List<Customers> getAllCustomers() {
		System.out.println("DB Called");
		return customersRepository.findAll();
	}

	// Retrieve a customer by ID
	@Cacheable(key = "#id", value = "customer")
	public Optional<Customers> getCustomerById(int id) {
		System.out.println("DB Called");
		return customersRepository.findById(id);
	}

	// Get user by login request
	public Optional<Customers> authenticateCustomer(LoginRequest loginRequest) {
		Customers customers =customersRepository.findCustomerByEmailAndPassword(loginRequest.getEmail(), loginRequest.getPassword());	
		return Optional.ofNullable(customers);
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
