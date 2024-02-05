package com.haven.controller;

import com.haven.postgress.model.Books;
import com.haven.postgress.service.BookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BooksController {

    @Autowired
    private BookService booksService;

    @PostMapping
    public ResponseEntity<Books> createBook(@RequestBody Books book) {
        Books createdBook = booksService.createBook(book);
        return new ResponseEntity<>(createdBook, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Books>> getAllBooks() {
        List<Books> books = booksService.getAllBooks();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Books> getBookById(@PathVariable int id) {
        Optional<Books> book = booksService.getBookById(id);
        return book.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    // Retrieve books by author ID
    @GetMapping("/byAuthor/{authorId}")
    public ResponseEntity<List<Books>> getBooksByAuthorId(@PathVariable int authorId) {
        List<Books> books = booksService.getBooksByAuthorId(authorId);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Books> updateBook(@PathVariable int id, @RequestBody Books updatedBook) {
        Books book = booksService.updateBook(id, updatedBook);
        return (book != null) ?
                new ResponseEntity<>(book, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable int id) {
        booksService.deleteBook(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
