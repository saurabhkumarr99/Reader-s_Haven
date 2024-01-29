package com.haven.service;

import com.haven.dao.BooksRepository;
import com.haven.model.Books;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BooksRepository booksRepository;

    // Create a book
    public Books createBook(Books book) {
        return booksRepository.save(book);
    }

    // Retrieve all books
    public List<Books> getAllBooks() {
        return booksRepository.findAll();
    }

    // Retrieve a book by ID
    public Optional<Books> getBookById(int id) {
        return booksRepository.findById(id);
    }
    
    // Retrieve books by author ID
    public List<Books> getBooksByAuthorId(int authorId) {
        return booksRepository.findByAuthorId(authorId);
    }


    // Update a book
    public Books updateBook(int id, Books updatedBook) {
        Optional<Books> optionalBook = booksRepository.findById(id);

        if (optionalBook.isPresent()) {
           
            return booksRepository.save(updatedBook);
        } else {
            // Handle the case where the book with the given ID is not found
            return null;
        }
    }

    // Delete a book by ID
    public void deleteBook(int id) {
        booksRepository.deleteById(id);
    }
}
