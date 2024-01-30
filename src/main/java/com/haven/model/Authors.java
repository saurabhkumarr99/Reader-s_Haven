package com.haven.model;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Authors implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int author_id;

	private String author_name;
	private String biography;
	private String awards;

	public Authors() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Authors(int author_id, String author_name, String biography, String awards) {
		super();
		this.author_id = author_id;
		this.author_name = author_name;
		this.biography = biography;
		this.awards = awards;
	}

	public int getAuthor_id() {
		return author_id;
	}

	public void setAuthor_id(int author_id) {
		this.author_id = author_id;
	}

	public String getAuthor_name() {
		return author_name;
	}

	public void setAuthor_name(String author_name) {
		this.author_name = author_name;
	}

	public String getBiography() {
		return biography;
	}

	public void setBiography(String biography) {
		this.biography = biography;
	}

	public String getAwards() {
		return awards;
	}

	public void setAwards(String awards) {
		this.awards = awards;
	}

	@Override
	public String toString() {
		return "authors [author_id=" + author_id + ", author_name=" + author_name + ", biography=" + biography
				+ ", awards=" + awards + "]";
	}

}
