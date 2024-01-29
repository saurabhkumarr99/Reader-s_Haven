package com.haven.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.haven.model.Cart;

public interface CartRepository extends JpaRepository<Cart, Integer> {

	// Custom query to find carts by customer ID
	@Query("from Cart c where c.customer_id = :customer_id")
	List<Cart> findByCustomer_id(String customer_id);

}
