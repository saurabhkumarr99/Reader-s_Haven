package com.haven.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.haven.cassandra.model.BookRecommendation;
import com.haven.cassandra.service.BookRecommendationService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/book-recommendations")
public class BookRecommendationController {

    @Autowired
    private BookRecommendationService bookRecommendationService;

    // Create
    @PostMapping
    public ResponseEntity<BookRecommendation> createBookRecommendation(@RequestBody BookRecommendation bookRecommendation) {
        BookRecommendation createdBookRecommendation = bookRecommendationService.saveOrUpdateBookRecommendation(bookRecommendation);
        return new ResponseEntity<>(createdBookRecommendation, HttpStatus.CREATED);
    }

    // Read
    @GetMapping
    public ResponseEntity<List<BookRecommendation>> getAllBookRecommendations() {
        List<BookRecommendation> bookRecommendations = bookRecommendationService.getAllBookRecommendations();
        return new ResponseEntity<>(bookRecommendations, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookRecommendation> getBookRecommendationById(@PathVariable int id) {
        Optional<BookRecommendation> bookRecommendation = bookRecommendationService.getBookRecommendationById(id);
        return bookRecommendation.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Update
    @PutMapping("/{id}")
    public ResponseEntity<BookRecommendation> updateBookRecommendation(@PathVariable int id, @RequestBody BookRecommendation updatedBookRecommendation) {
        updatedBookRecommendation.setId(id);
        BookRecommendation result = bookRecommendationService.updateBookRecommendation(updatedBookRecommendation);
        return result != null
                ? new ResponseEntity<>(result, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBookRecommendation(@PathVariable int id) {
        bookRecommendationService.deleteBookRecommendationById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
