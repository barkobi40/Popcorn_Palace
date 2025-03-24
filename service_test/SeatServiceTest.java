package com.tdp.popcorn_palace.service_test;

import com.tdp.popcorn_palace.model.Seat;
import com.tdp.popcorn_palace.model.Showtime;
import com.tdp.popcorn_palace.repository.SeatRepository;
import com.tdp.popcorn_palace.repository.ShowtimeRepository;
import com.tdp.popcorn_palace.service.SeatService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class SeatServiceTest {

    @InjectMocks
    private SeatService seatService;

    @Mock
    private SeatRepository seatRepository;

    @Mock
    private ShowtimeRepository showtimeRepository;

    private Showtime showtime;
    private Seat seat;
    private UUID showtimeId;
    private String seatId;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        showtimeId = UUID.randomUUID();
        showtime = new Showtime();
        showtime.setId(showtimeId);
        seatId = UUID.randomUUID().toString();
        seat = new Seat();
        seat.setId(seatId);
        seat.setShowtime(showtime);
        seat.setSeatNumber("A1");
        seat.setBooked(false);
    }

    @Test
    void getSeatsByShowtime() {
        when(seatRepository.findByShowtimeId(showtimeId)).thenReturn(Arrays.asList(seat));
        
        var seats = seatService.getSeatsByShowtime(showtimeId.toString());

        assertNotNull(seats);
        assertEquals(1, seats.size());
        assertEquals("A1", seats.get(0).getSeatNumber());
        verify(seatRepository, times(1)).findByShowtimeId(showtimeId);
    }

    @Test
    void createSeat() {
        when(showtimeRepository.findById(showtimeId)).thenReturn(Optional.of(showtime));
        when(seatRepository.findByShowtimeIdAndSeatNumber(showtimeId.toString(), "A1")).thenReturn(Optional.empty());
        when(seatRepository.save(any(Seat.class))).thenReturn(seat);

        var createdSeat = seatService.createSeat(showtimeId.toString(), "A1");

        assertNotNull(createdSeat);
        assertEquals("A1", createdSeat.getSeatNumber());
        verify(seatRepository, times(1)).save(any(Seat.class));
    }

    @Test
    void bookSeat() {
        when(seatRepository.findById(seatId)).thenReturn(Optional.of(seat));
        when(seatRepository.save(any(Seat.class))).thenReturn(seat);

        seat.setBooked(false);
        var bookedSeat = seatService.bookSeat(seatId, "customerId");

        assertTrue(bookedSeat.isBooked());
        assertNotNull(bookedSeat.getCustomerId());
        verify(seatRepository, times(1)).save(any(Seat.class));
    }

    @Test
    void cancelSeat() {
        when(seatRepository.findById(seatId)).thenReturn(Optional.of(seat));
        when(seatRepository.save(any(Seat.class))).thenReturn(seat);

        seat.setBooked(true);
        var canceledSeat = seatService.cancelSeat(seatId);

        assertFalse(canceledSeat.isBooked());
        assertNull(canceledSeat.getCustomerId());
        verify(seatRepository, times(1)).save(any(Seat.class));
    }

    @Test
    void bookSeat_alreadyBooked() {
        seat.setBooked(true);
        when(seatRepository.findById(seatId)).thenReturn(Optional.of(seat));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> seatService.bookSeat(seatId, "customerId"));
        assertEquals("Seat already booked", exception.getMessage());
    }

    @Test
    void createSeat_showtimeNotFound() {
        when(showtimeRepository.findById(showtimeId)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> seatService.createSeat(showtimeId.toString(), "A1"));
        assertEquals("Showtime not found", exception.getMessage());
    }

    @Test
    void createSeat_seatExists() {
        when(showtimeRepository.findById(showtimeId)).thenReturn(Optional.of(showtime));
        when(seatRepository.findByShowtimeIdAndSeatNumber(showtimeId.toString(), "A1")).thenReturn(Optional.of(seat));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> seatService.createSeat(showtimeId.toString(), "A1"));
        assertEquals("Seat already exists", exception.getMessage());
    }

    @Test
    void cancelSeat_seatNotFound() {
        when(seatRepository.findById(seatId)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> seatService.cancelSeat(seatId));
        assertEquals("Seat not found", exception.getMessage());
    }

    @Test
    void bookSeat_seatNotFound() {
        when(seatRepository.findById(seatId)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> seatService.bookSeat(seatId, "customerId"));
        assertEquals("Seat not found", exception.getMessage());
    }
}
