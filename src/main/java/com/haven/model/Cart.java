package com.haven.model;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Cart implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cart_id;

	private String customer_id;
	private String book_id;
	private String quantity;
	private String added_to_cart;

	public Cart() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Cart(int cart_id, String customer_id, String book_id, String quantity, String added_to_cart) {
		super();
		this.cart_id = cart_id;
		this.customer_id = customer_id;
		this.book_id = book_id;
		this.quantity = quantity;
		this.added_to_cart = added_to_cart;
	}

	public int getCart_id() {
		return cart_id;
	}

	public void setCart_id(int cart_id) {
		this.cart_id = cart_id;
	}

	public String getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}

	public String getBook_id() {
		return book_id;
	}

	public void setBook_id(String book_id) {
		this.book_id = book_id;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getAdded_to_cart() {
		return added_to_cart;
	}

	public void setAdded_to_cart(String added_to_cart) {
		this.added_to_cart = added_to_cart;
	}

	@Override
	public String toString() {
		return "cart [cart_id=" + cart_id + ", customer_id=" + customer_id + ", book_id=" + book_id + ", quantity="
				+ quantity + ", added_to_cart=" + added_to_cart + "]";
	}

}
