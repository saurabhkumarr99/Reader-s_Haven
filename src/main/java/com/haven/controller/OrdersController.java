package com.haven.controller;

import com.haven.kafka.service.producer.OrderProducerService;
import com.haven.postgress.model.Orders;
import com.haven.postgress.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/orders")
public class OrdersController {

	@Autowired
	private OrderService orderService;

	@Autowired
	OrderProducerService orderProducerService;

	@PostMapping
	/**
	 * This method process order update inventory and send mail notification 
	 * @param order
	 * @return
	 */
	public ResponseEntity<String> createOrder(@RequestBody Orders order) {

		orderProducerService.handelNewOrder(order);
		
		return new ResponseEntity<>("Order created successfully", HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<Orders>> getAllOrders() {
		List<Orders> orders = orderService.getAllOrders();
		return new ResponseEntity<>(orders, HttpStatus.OK);
	}

	@GetMapping("/getByCustomerId/{customerId}")
	public ResponseEntity<List<Orders>> getOrderByCustomerId(@PathVariable String customerId) {
		List<Orders> orderItems = orderService.getOrdersByCustomerId(customerId);
		return new ResponseEntity<>(orderItems, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Orders> getOrderById(@PathVariable int id) {
		Optional<Orders> order = orderService.getOrderById(id);
		return order.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
				.orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Orders> updateOrder(@PathVariable int id, @RequestBody Orders updatedOrder) {
		Orders order = orderService.updateOrder(id, updatedOrder);
		return (order != null) ? new ResponseEntity<>(order, HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteOrder(@PathVariable int id) {
		orderService.deleteOrder(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
