package com.haven.postgress.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.haven.postgress.model.Customers;

public interface CustomersRepository extends JpaRepository<Customers, Integer> {

	// Custom query to get customer by email and password
	@Query("from Customers c where c.email = :email and c.password = :password")
	Customers findCustomerByEmailAndPassword(@Param("email") String email, @Param("password") String password);
}
