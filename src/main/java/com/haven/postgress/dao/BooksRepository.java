package com.haven.postgress.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.haven.postgress.model.Books;

public interface BooksRepository extends JpaRepository<Books, Integer> {

	// Custom query to find books by author ID
	@Query("from Books b where b.author_id = :author_id")
	List<Books> findByAuthorId(int author_id);

}
