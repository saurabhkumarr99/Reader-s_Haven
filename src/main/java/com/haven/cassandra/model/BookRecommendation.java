package com.haven.cassandra.model;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table
public class BookRecommendation {

	@PrimaryKey
	private int id;
	
	private int customer_id;
	private int book_id;
	private String recommendationType;

	public BookRecommendation() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BookRecommendation(int id, int customer_id, int book_id, String recommendationType) {
		super();
		this.id = id;
		this.customer_id = customer_id;
		this.book_id = book_id;
		this.recommendationType = recommendationType;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}

	public int getBook_id() {
		return book_id;
	}

	public void setBook_id(int book_id) {
		this.book_id = book_id;
	}

	public String getRecommendationType() {
		return recommendationType;
	}

	public void setRecommendationType(String recommendationType) {
		this.recommendationType = recommendationType;
	}

	@Override
	public String toString() {
		return "BookRecommendation [id=" + id + ", customer_id=" + customer_id + ", book_id=" + book_id
				+ ", recommendationType=" + recommendationType + "]";
	}

}
