package com.tdp.popcorn_palace.service;

import com.tdp.popcorn_palace.model.Seat;
import com.tdp.popcorn_palace.model.Showtime;
import com.tdp.popcorn_palace.repository.SeatRepository;
import com.tdp.popcorn_palace.repository.ShowtimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SeatService {

    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private ShowtimeRepository showtimeRepository;

    public List<Seat> getSeatsByShowtime(String showtimeId) {
        return seatRepository.findByShowtimeId(UUID.fromString(showtimeId));
    }

    public Seat createSeat(String showtimeId, String seatNumber) {
        UUID showtimeUUID = UUID.fromString(showtimeId);
        Showtime showtime = showtimeRepository.findById(showtimeUUID)
                .orElseThrow(() -> new RuntimeException("Showtime not found"));

        seatRepository.findByShowtimeIdAndSeatNumber(showtimeUUID, seatNumber)
                .ifPresent(s -> {
                    throw new RuntimeException("Seat already exists");
                });

        Seat seat = new Seat();
        seat.setSeatNumber(seatNumber);
        seat.setShowtime(showtime);
        seat.setBooked(false);

        return seatRepository.save(seat);
    }

    public Seat bookSeat(String seatId, String customerId) {
        Seat seat = seatRepository.findById(UUID.fromString(seatId))
                .orElseThrow(() -> new RuntimeException("Seat not found"));

        if (seat.isBooked()) {
            throw new RuntimeException("Seat already booked");
        }

        seat.setBooked(true);
        seat.setCustomerId(customerId);
        return seatRepository.save(seat);
    }

    public Seat cancelSeat(String seatId) {
        Seat seat = seatRepository.findById(UUID.fromString(seatId))
                .orElseThrow(() -> new RuntimeException("Seat not found"));

        seat.setBooked(false);
        seat.setCustomerId(null);
        return seatRepository.save(seat);
    }
}
