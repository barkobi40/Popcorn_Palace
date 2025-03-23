package com.tdp.popcorn_palace.repository;

import com.tdp.popcorn_palace.model.Showtime;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ShowtimeRepository extends JpaRepository<Showtime, Long> {

    List<Showtime> findByTheater(String theater);

    List<Showtime> findByTheaterAndStartTimeLessThanAndEndTimeGreaterThan(
        String theater,
        LocalDateTime endTime,
        LocalDateTime startTime
    );
}
