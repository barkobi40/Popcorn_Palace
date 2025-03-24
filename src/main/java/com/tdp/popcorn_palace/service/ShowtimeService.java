package com.tdp.popcorn_palace.service;

import com.tdp.popcorn_palace.model.Showtime;
import com.tdp.popcorn_palace.repository.ShowtimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ShowtimeService {

    @Autowired
    private ShowtimeRepository showtimeRepository;

    public List<Showtime> getAllShowtimes() {
        return showtimeRepository.findAll();
    }

    public Showtime getShowtimeById(UUID id) {
        return showtimeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Showtime not found"));
    }

    public Showtime createShowtime(Showtime showtime) {
        return showtimeRepository.save(showtime);
    }

    public Showtime updateShowtime(UUID id, Showtime updatedShowtime) {
        Showtime existing = getShowtimeById(id);
        existing.setMovie(updatedShowtime.getMovie());
        existing.setTheater(updatedShowtime.getTheater());
        existing.setStartTime(updatedShowtime.getStartTime());
        existing.setEndTime(updatedShowtime.getEndTime());
        existing.setPrice(updatedShowtime.getPrice());
        return showtimeRepository.save(existing);
    }

    public void deleteShowtime(UUID id) {
        showtimeRepository.deleteById(id);
    }
}
