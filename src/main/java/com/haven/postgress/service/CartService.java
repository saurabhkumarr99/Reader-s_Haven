package com.haven.postgress.service;

import com.haven.postgress.dao.CartRepository;
import com.haven.postgress.model.Cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    // Create a cart entry
    public Cart addToCart(Cart cartItem) {
        return cartRepository.save(cartItem);
    }

    // Retrieve all cart entries for a customer
    public List<Cart> getAllCart() {
        return cartRepository.findAll();
    }

    // Retrieve all cart entries for a customer
    @Cacheable(key = "#customer_id",value = "carts")
    public List<Cart> getCartByCustomerId(String customer_id) {
    	System.out.println("DB Called");
        return cartRepository.findByCustomer_id(customer_id);
    }


    // Update a cart entry
    public Cart updateCartItem(int id, Cart updatedCartItem) {
        Optional<Cart> optionalCartItem = cartRepository.findById(id);

        if (optionalCartItem.isPresent()) {

            return cartRepository.save(updatedCartItem);
        } else {
            // Handle the case where the cart entry with the given ID is not found
            return null;
        }
    }

    // Delete a cart entry by ID
    public void deleteCartItem(int id) {
        cartRepository.deleteById(id);
    }
}
