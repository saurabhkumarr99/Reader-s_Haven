package com.haven.controller;

import com.haven.postgress.model.Authors;
import com.haven.postgress.service.AuthorsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/authors")
public class AuthorsController {

    @Autowired
    private AuthorsService authorsService;

    @PostMapping
    public ResponseEntity<Authors> createAuthor(@RequestBody Authors author) {
        Authors createdAuthor = authorsService.createAuthor(author);
        return new ResponseEntity<Authors>(createdAuthor, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Authors>> getAllAuthors() {
        List<Authors> authors = authorsService.getAllAuthors();
        return new ResponseEntity<List<Authors>>(authors, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Authors> getAuthorById(@PathVariable int id) {
        Optional<Authors> author = authorsService.getAuthorById(id);
        if (author.isPresent()) {
            return new ResponseEntity<Authors>(author.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<Authors>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Authors> updateAuthor(@PathVariable int id, @RequestBody Authors updatedAuthor) {
        Authors author = authorsService.updateAuthor(id, updatedAuthor);
        if (author != null) {
            return new ResponseEntity<Authors>(author, HttpStatus.OK);
        } else {
            return new ResponseEntity<Authors>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable int id) {
        authorsService.deleteAuthor(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
}
