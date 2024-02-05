package com.haven.postgress.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;

@Entity
public class Inventory implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int book_id;

	private String quantity;
	private String price;
	private String last_updated;

	public Inventory() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Inventory(int book_id, String quantity, String price, String last_updated) {
		super();
		this.book_id = book_id;
		this.quantity = quantity;
		this.price = price;
		this.last_updated = last_updated;
	}

	public int getBook_id() {
		return book_id;
	}

	public void setBook_id(int book_id) {
		this.book_id = book_id;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getLast_updated() {
		return last_updated;
	}

	public void setLast_updated(String last_updated) {
		this.last_updated = last_updated;
	}

	@Override
	public String toString() {
		return "inventory [book_id=" + book_id + ", quantity=" + quantity + ", price=" + price + ", last_updated="
				+ last_updated + "]";
	}

}
