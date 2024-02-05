package com.haven.controller;

import com.haven.postgress.model.Inventory;
import com.haven.postgress.service.InventoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @PostMapping
    public ResponseEntity<Inventory> addToInventory(@RequestBody Inventory inventoryItem) {
        Inventory addedToInventory = inventoryService.addToInventory(inventoryItem);
        return new ResponseEntity<>(addedToInventory, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Inventory>> getAllInventory() {
        List<Inventory> inventoryItems = inventoryService.getAllInventory();
        return new ResponseEntity<>(inventoryItems, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Inventory> getInventoryItemById(@PathVariable int id) {
        Optional<Inventory> inventoryItem = inventoryService.getInventoryItemById(id);
        return inventoryItem.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Inventory> updateInventoryItem(@PathVariable int id, @RequestBody Inventory updatedInventoryItem) {
        Inventory inventoryItem = inventoryService.updateInventoryItem(id, updatedInventoryItem);
        return (inventoryItem != null) ?
                new ResponseEntity<>(inventoryItem, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInventoryItem(@PathVariable int id) {
        inventoryService.deleteInventoryItem(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
