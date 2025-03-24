package com.tdp.popcorn_palace.service_test;

import com.tdp.popcorn_palace.model.Movie;
import com.tdp.popcorn_palace.model.Showtime;
import com.tdp.popcorn_palace.repository.MovieRepository;
import com.tdp.popcorn_palace.repository.ShowtimeRepository;
import com.tdp.popcorn_palace.service.ShowtimeService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class ShowtimeServiceTest {

    @InjectMocks
    private ShowtimeService showtimeService;

    @Mock
    private ShowtimeRepository showtimeRepository;

    @Mock
    private MovieRepository movieRepository;

    private Showtime showtime;
    private Movie movie;
    private UUID showtimeId;
    private UUID movieId;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        showtimeId = UUID.randomUUID();
        movieId = UUID.randomUUID();
        movie = new Movie();
        movie.setId(movieId);
        showtime = new Showtime();
        showtime.setId(showtimeId);
        showtime.setMovie(movie);
    }

    @Test
    void createShowtime() {
        when(movieRepository.findById(movieId)).thenReturn(Optional.of(movie));
        when(showtimeRepository.save(any(Showtime.class))).thenReturn(showtime);

        Showtime createdShowtime = showtimeService.createShowtime(movieId.toString(), "10:00 AM", "12:00 PM", 15.5);

        assertNotNull(createdShowtime);
        assertEquals("10:00 AM", createdShowtime.getStartTime());
        assertEquals("12:00 PM", createdShowtime.getEndTime());
        verify(showtimeRepository, times(1)).save(any(Showtime.class));
    }

    @Test
    void createShowtime_movieNotFound() {
        when(movieRepository.findById(movieId)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> showtimeService.createShowtime(movieId.toString(), "10:00 AM", "12:00 PM", 15.5));
        assertEquals("Movie not found", exception.getMessage());
    }

    @Test
    void getShowtimeById() {
        when(showtimeRepository.findById(showtimeId)).thenReturn(Optional.of(showtime));

        Showtime foundShowtime = showtimeService.getShowtimeById(showtimeId.toString());

        assertNotNull(foundShowtime);
        assertEquals(showtimeId, foundShowtime.getId());
    }

    @Test
    void getShowtimeById_notFound() {
        when(showtimeRepository.findById(showtimeId)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> showtimeService.getShowtimeById(showtimeId.toString()));
        assertEquals("Showtime not found", exception.getMessage());
    }

    @Test
    void createShowtime_withOverlap_shouldThrowException() {
    when(movieRepository.findById(movieId)).thenReturn(Optional.of(movie));

    Showtime existingShowtime = new Showtime();
    existingShowtime.setTheater("Cinema 1");
    existingShowtime.setStartTime("10:00 AM");
    existingShowtime.setEndTime("12:00 PM");

    when(showtimeRepository.findByTheater("Cinema 1"))
        .thenReturn(List.of(existingShowtime));

    RuntimeException exception = assertThrows(RuntimeException.class, () ->
        showtimeService.createShowtime(movieId.toString(), "11:00 AM", "01:00 PM", 20.0));

    assertEquals("Overlapping showtime in same theater", exception.getMessage());
}

}
