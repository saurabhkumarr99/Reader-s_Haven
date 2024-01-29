package com.haven.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.haven.model.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Integer>{

}
