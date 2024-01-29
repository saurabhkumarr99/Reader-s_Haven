package com.haven.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.haven.model.Cart;
import com.haven.model.Orders;

public interface OrdersRepository extends JpaRepository<Orders, Integer> {

	// Custom query to find carts by customer ID
	@Query("from Orders o where o.customer_id = :customer_id")
	List<Orders> findByCustomer_id(String customer_id);
}
