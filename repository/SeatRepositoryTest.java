package com.tdp.popcorn_palace.repository;

import com.tdp.popcorn_palace.model.Seat;
import com.tdp.popcorn_palace.model.Showtime;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class SeatRepositoryTest {

    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private ShowtimeRepository showtimeRepository;

    @Test
    public void testFindByShowtimeId() {
        UUID showtimeId = UUID.randomUUID();

        Showtime showtime = new Showtime();
        showtime.setId(showtimeId);
        showtimeRepository.save(showtime);

        Seat seat1 = new Seat();
        seat1.setShowtime(showtime);
        seat1.setSeatNumber("A1");
        seatRepository.save(seat1);

        Seat seat2 = new Seat();
        seat2.setShowtime(showtime);
        seat2.setSeatNumber("A2");
        seatRepository.save(seat2);

        List<Seat> seats = seatRepository.findByShowtimeId(showtimeId);

        assertNotNull(seats);
        assertEquals(2, seats.size());
    }

    @Test
    public void testFindByShowtimeIdAndSeatNumber() {
        UUID showtimeId = UUID.randomUUID();
        Showtime showtime = new Showtime();
        showtime.setId(showtimeId);
        showtimeRepository.save(showtime);

        Seat seat = new Seat();
        seat.setShowtime(showtime);
        seat.setSeatNumber("A1");
        seatRepository.save(seat);

        Seat foundSeat = seatRepository.findByShowtimeIdAndSeatNumber(showtimeId, "A1").orElse(null);

        assertNotNull(foundSeat);
        assertEquals("A1", foundSeat.getSeatNumber());
    }
}
