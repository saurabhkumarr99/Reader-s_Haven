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
public class Books implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int book_id;

	private String title;
	private String author_id;
	private String genre;
	private String publication_date;
	private String price;
	private String description;

	public Books() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Books(int book_id, String title, String author_id, String genre, String publication_date, String price,
			String description) {
		super();
		this.book_id = book_id;
		this.title = title;
		this.author_id = author_id;
		this.genre = genre;
		this.publication_date = publication_date;
		this.price = price;
		this.description = description;
	}

	public int getBook_id() {
		return book_id;
	}

	public void setBook_id(int book_id) {
		this.book_id = book_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor_id() {
		return author_id;
	}

	public void setAuthor_id(String author_id) {
		this.author_id = author_id;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getPublication_date() {
		return publication_date;
	}

	public void setPublication_date(String publication_date) {
		this.publication_date = publication_date;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "books [book_id=" + book_id + ", title=" + title + ", author_id=" + author_id + ", genre=" + genre
				+ ", publication_date=" + publication_date + ", price=" + price + ", description=" + description + "]";
	}

}
