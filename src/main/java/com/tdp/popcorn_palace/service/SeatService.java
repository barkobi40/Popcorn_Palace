package com.tdp.popcorn_palace.service;

import com.tdp.popcorn_palace.model.Seat;
import com.tdp.popcorn_palace.model.Showtime;
import com.tdp.popcorn_palace.repository.SeatRepository;
import com.tdp.popcorn_palace.repository.ShowtimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatService {

    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private ShowtimeRepository showtimeRepository;

    public Seat createSeat(Long showtimeId, int seatNumber) {
        Showtime showtime = showtimeRepository.findById(showtimeId)
            .orElseThrow(() -> new RuntimeException("Showtime not found"));

        seatRepository.findByShowtimeIdAndSeatNumber(showtimeId, seatNumber)
            .ifPresent(s -> { throw new RuntimeException("Seat already exists"); });

        Seat seat = new Seat();
        seat.setSeatNumber(seatNumber);
        seat.setShowtime(showtime);
        return seatRepository.save(seat);
    }

    public List<Seat> getSeatsByShowtime(Long showtimeId) {
        return seatRepository.findByShowtimeId(showtimeId);
    }

    public Seat bookSeat(Long seatId, String customerId) {
        Seat seat = seatRepository.findById(seatId)
            .orElseThrow(() -> new RuntimeException("Seat not found"));

        if (seat.isBooked()) {
            throw new RuntimeException("Seat already booked");
        }

        seat.setBooked(true);
        seat.setCustomerId(customerId);
        return seatRepository.save(seat);
    }

    public Seat cancelSeat(Long seatId) {
        Seat seat = seatRepository.findById(seatId)
            .orElseThrow(() -> new RuntimeException("Seat not found"));

        seat.setBooked(false);
        seat.setCustomerId(null);
        return seatRepository.save(seat);
    }
}
