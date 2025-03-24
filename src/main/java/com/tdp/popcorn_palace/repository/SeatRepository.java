package com.tdp.popcorn_palace.repository;

import com.tdp.popcorn_palace.model.Seat;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SeatRepository extends CrudRepository<Seat, UUID> {
    List<Seat> findByShowtimeId(UUID showtimeId);
    Optional<Seat> findByShowtimeIdAndSeatNumber(UUID showtimeId, String seatNumber);
}
