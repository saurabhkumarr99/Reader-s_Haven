package com.haven.service;

import com.haven.dao.OrdersRepository;
import com.haven.model.Cart;
import com.haven.model.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

	@Autowired
	private OrdersRepository ordersRepository;

	// Create an order
	public Orders createOrder(Orders order) {
		return ordersRepository.save(order);
	}

	// Retrieve all orders
	public List<Orders> getAllOrders() {
		return ordersRepository.findAll();
	}

	// Retrieve an order by ID
	public Optional<Orders> getOrderById(int id) {
		return ordersRepository.findById(id);
	}

	// Retrieve all orders entries for a customer
	public List<Orders> getOrdersByCustomerId(String customer_id) {
		return ordersRepository.findByCustomer_id(customer_id);
	}

	// Update an order
	public Orders updateOrder(int id, Orders updatedOrder) {
		Optional<Orders> optionalOrder = ordersRepository.findById(id);

		if (optionalOrder.isPresent()) {
			Orders existingOrder = optionalOrder.get();
			existingOrder.setCustomer_id(updatedOrder.getCustomer_id());
			existingOrder.setOrder_date(updatedOrder.getOrder_date());
			existingOrder.setStatus(updatedOrder.getStatus());
			// Set other properties as needed

			return ordersRepository.save(existingOrder);
		} else {
			// Handle the case where the order with the given ID is not found
			return null;
		}
	}

	// Delete an order by ID
	public void deleteOrder(int id) {
		ordersRepository.deleteById(id);
	}
}
