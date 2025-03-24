package com.tdp.popcorn_palace.controller;

import com.tdp.popcorn_palace.model.Movie;
import com.tdp.popcorn_palace.service.MovieService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class MovieControllerTest {

    @Mock
    private MovieService movieService;

    @InjectMocks
    private MovieController movieController;

    @Test
    public void testGetAllMovies() {
        Movie movie = new Movie();
        movie.setTitle("Inception");

        when(movieService.getAllMovies()).thenReturn(List.of(movie));

        var response = movieController.getAllMovies();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().size());
        assertEquals("Inception", response.getBody().get(0).getTitle());
    }

    @Test
    public void testDeleteMovie() {
        UUID movieId = UUID.randomUUID();

        doNothing().when(movieService).deleteMovie(movieId);

        var response = movieController.deleteMovie(movieId);

        assertEquals(204, response.getStatusCodeValue());
    }
}
