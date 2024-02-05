package com.haven.postgress.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import com.haven.postgress.dao.AuthorsRepository;
import com.haven.postgress.model.Authors;

@Service
public class AuthorsService {

	@Autowired
	private AuthorsRepository authorsRepository;

	// Create an author
	public Authors createAuthor(Authors author) {
		return authorsRepository.save(author);
	}

	// Retrieve all authors
	@Cacheable(key = "'allAuthor'" ,value = "authorList")
	public List<Authors> getAllAuthors() {
		System.out.println("DB Called");
		return authorsRepository.findAll();
	}

	/**
	 * @author Saurabh Rai
	 * @apiNote an author by ID , storing data in redis as catch
	 * @param id
	 * @return Author
	 */
	@Cacheable(key = "#id" ,value = "author")
	public Optional<Authors> getAuthorById(int id) {
		System.out.println("DB Called");
		return authorsRepository.findById(id);
	}

	// Update an author
	public Authors updateAuthor(int id, Authors updatedAuthor) {
		Optional<Authors> optionalAuthor = authorsRepository.findById(id);

		if (optionalAuthor.isPresent()) {
		
			return authorsRepository.save(updatedAuthor);
		} else {
			// Handle the case where the author with the given ID is not found
			return null;
		}
	}

	// Delete an author by ID
	public void deleteAuthor(int id) {
		authorsRepository.deleteById(id);
	}
}
