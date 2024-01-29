package com.haven.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.haven.model.Customers;

public interface CustomersRepository extends JpaRepository<Customers, Integer>{

}
