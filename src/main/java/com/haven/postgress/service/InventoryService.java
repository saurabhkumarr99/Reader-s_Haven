package com.haven.postgress.service;

import com.haven.postgress.dao.InventoryRepository;
import com.haven.postgress.model.Inventory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    // Create an inventory entry
    public Inventory addToInventory(Inventory inventoryItem) {
        return inventoryRepository.save(inventoryItem);
    }

    // Retrieve all inventory entries
    @Cacheable(key = "'inventoryList'" ,value = "allInventory")
    public List<Inventory> getAllInventory() {
    	System.out.println("DB Called");
        return inventoryRepository.findAll();
    }

    // Retrieve an inventory entry by book ID
    @Cacheable(key = "#id" , value = "inventory")
    public Optional<Inventory> getInventoryItemById(int id) {
        return inventoryRepository.findById(id);
    }

    // Update an inventory entry
    public Inventory updateInventoryItem(int id, Inventory updatedInventoryItem) {
        Optional<Inventory> optionalInventoryItem = inventoryRepository.findById(id);

        if (optionalInventoryItem.isPresent()) {

            return inventoryRepository.save(updatedInventoryItem);
        } else {
            // Handle the case where the inventory entry with the given ID is not found
            return null;
        }
    }

    // Delete an inventory entry by book ID
    public void deleteInventoryItem(int id) {
        inventoryRepository.deleteById(id);
    }
}
