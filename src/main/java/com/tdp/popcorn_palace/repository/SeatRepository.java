package com.tdp.popcorn_palace.repository;

import com.tdp.popcorn_palace.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SeatRepository extends JpaRepository<Seat, Long> {
    List<Seat> findByShowtimeId(Long showtimeId);
    Optional<Seat> findByShowtimeIdAndSeatNumber(Long showtimeId, int seatNumber);
}
