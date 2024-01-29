package com.haven.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import com.haven.dao.AuthorsRepository;
import com.haven.model.Authors;

@Service
public class AuthorsService {

	@Autowired
	private AuthorsRepository authorsRepository;

	// Create an author
	public Authors createAuthor(Authors author) {
		return authorsRepository.save(author);
	}

	// Retrieve all authors
	public List<Authors> getAllAuthors() {
		return authorsRepository.findAll();
	}

	// Retrieve an author by ID
	public Optional<Authors> getAuthorById(int id) {
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
