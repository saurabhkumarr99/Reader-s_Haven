package com.haven.controller;

import com.haven.postgress.model.Cart;
import com.haven.postgress.service.CartService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping
    public ResponseEntity<Cart> addToCart(@RequestBody Cart cartItem) {
        Cart addedToCart = cartService.addToCart(cartItem);
        return new ResponseEntity<>(addedToCart, HttpStatus.CREATED);
    }
    
    @GetMapping
    public ResponseEntity<List<Cart>> getAllCarts(){
    	List<Cart> allCarts=cartService.getAllCart();
    	return new ResponseEntity<>(allCarts,HttpStatus.OK);
    }

    @GetMapping("/getByCustomerId/{customerId}")
    public ResponseEntity<List<Cart>> getCartByCustomerId(@PathVariable String customerId) {
        List<Cart> cartItems = cartService.getCartByCustomerId(customerId);
        return new ResponseEntity<>(cartItems, HttpStatus.OK);
    }

//    @GetMapping("/item/{id}")
//    public ResponseEntity<Cart> getCartItemById(@PathVariable int id) {
//        Optional<Cart> cartItem = cartService.getCartItemById(id);
//        return cartItem.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
//                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
//    }

    @PutMapping("/item/{id}")
    public ResponseEntity<Cart> updateCartItem(@PathVariable int id, @RequestBody Cart updatedCartItem) {
        Cart cartItem = cartService.updateCartItem(id, updatedCartItem);
        return (cartItem != null) ?
                new ResponseEntity<>(cartItem, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/item/{id}")
    public ResponseEntity<Void> deleteCartItem(@PathVariable int id) {
        cartService.deleteCartItem(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
