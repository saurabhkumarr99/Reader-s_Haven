package com.haven.postgress.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.haven.postgress.model.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Integer>{

}
