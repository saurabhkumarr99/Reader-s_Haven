package com.haven.postgress.service;

import com.haven.postgress.dao.BooksRepository;
import com.haven.postgress.model.Books;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
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
    @Cacheable(key = "'allBooks'",value = "allBooksCache")
    public List<Books> getAllBooks() {
    	System.out.println("Db called");
        return booksRepository.findAll();
    }

    // Retrieve a book by ID
    public Optional<Books> getBookById(int id) {
        return booksRepository.findById(id);
    }
    
    // Retrieve books by author ID
    @Cacheable(key = "#authorId" , value = "books")
    public List<Books> getBooksByAuthorId(int authorId) {
    	System.out.println("DB Called");
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
