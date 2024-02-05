package com.haven.cassandra.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.haven.cassandra.dao.BookRecommendationRepository;
import com.haven.cassandra.model.BookRecommendation;

import java.util.List;
import java.util.Optional;

@Service
public class BookRecommendationService {

    @Autowired
    private BookRecommendationRepository bookRecommendationRepository;

    // Create
    public BookRecommendation saveOrUpdateBookRecommendation(BookRecommendation bookRecommendation) {
        return bookRecommendationRepository.save(bookRecommendation);
    }

    // Read
    public List<BookRecommendation> getAllBookRecommendations() {
        return (List<BookRecommendation>) bookRecommendationRepository.findAll();
    }

    public Optional<BookRecommendation> getBookRecommendationById(int id) {
        return bookRecommendationRepository.findById(id);
    }

//    public List<BookRecommendation> getBookRecommendationsByCustomerId(int customerId) {
//        return bookRecommendationRepository.findByCustomer_Id(customerId);
//    }

    // Update
    public BookRecommendation updateBookRecommendation(BookRecommendation bookRecommendation) {
        if (bookRecommendationRepository.existsById(bookRecommendation.getId())) {
            return bookRecommendationRepository.save(bookRecommendation);
        }
        // Handle non-existing book recommendation
        return null;
    }

    // Delete
    public void deleteBookRecommendationById(int id) {
        bookRecommendationRepository.deleteById(id);
    }

}
