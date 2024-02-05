package com.haven.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.haven.postgress.dao.AuthorsRepository;
import com.haven.postgress.model.Authors;
import com.haven.postgress.service.AuthorsService;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class AuthorsServiceTest {

    @Mock
    private AuthorsRepository authorsRepository;

    @InjectMocks
    private AuthorsService authorsService;

    @Test
    public void testGetAuthorById() {
    	
        // Mocking the behavior of the repository
        Authors author = new Authors(1, "John Doe","Bio","award");
        when(authorsRepository.findById(1)).thenReturn(Optional.of(author));

        // First call to getAuthorById - DB should be called
        Optional<Authors> result1 = authorsService.getAuthorById(1);


        // Second call to getAuthorById - DB should not be called, and data should be fetched from cache
        Optional<Authors> result2 = authorsService.getAuthorById(1);
     
        // Assertions
        assertThat(result1).isEqualTo(Optional.of(author));
        assertThat(result2).isEqualTo(Optional.of(author));
    }
}
