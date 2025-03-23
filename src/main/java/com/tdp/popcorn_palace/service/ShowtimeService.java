package com.tdp.popcorn_palace.service;

import com.tdp.popcorn_palace.model.Movie;
import com.tdp.popcorn_palace.model.Showtime;
import com.tdp.popcorn_palace.repository.MovieRepository;
import com.tdp.popcorn_palace.repository.ShowtimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ShowtimeService {

    @Autowired
    private ShowtimeRepository showtimeRepository;

    @Autowired
    private MovieRepository movieRepository;

    public Showtime addShowtime(Long movieId, String theater, LocalDateTime startTime, LocalDateTime endTime, double price) {
        List<Showtime> overlapping = showtimeRepository.findByTheaterAndStartTimeLessThanAndEndTimeGreaterThan(
            theater, endTime, startTime
        );

        if (!overlapping.isEmpty()) {
            throw new RuntimeException("Overlapping showtime exists in this theater.");
        }

        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new RuntimeException("Movie not found"));

        Showtime showtime = new Showtime();
        showtime.setMovie(movie);
        showtime.setTheater(theater);
        showtime.setStartTime(startTime);
        showtime.setEndTime(endTime);
        showtime.setPrice(price);

        return showtimeRepository.save(showtime);
    }

    public List<Showtime> getAllShowtimes() {
        return showtimeRepository.findAll();
    }

    public Showtime updateShowtime(Long id, LocalDateTime start, LocalDateTime end, double price) {
        Showtime s = showtimeRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Showtime not found"));

        s.setStartTime(start);
        s.setEndTime(end);
        s.setPrice(price);
        return showtimeRepository.save(s);
    }

    public void deleteShowtime(Long id) {
        showtimeRepository.deleteById(id);
    }

    public Showtime getShowtimeById(Long id) {
        return showtimeRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Showtime not found"));
    }
}
