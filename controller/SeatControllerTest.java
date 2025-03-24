package com.tdp.popcorn_palace.controller;

import com.tdp.popcorn_palace.model.Seat;
import com.tdp.popcorn_palace.service.SeatService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class SeatControllerTest {

    @Mock
    private SeatService seatService;

    @InjectMocks
    private SeatController seatController;

    @Test
    public void testGetSeatsByShowtimeId() {
        UUID showtimeId = UUID.randomUUID();
        Seat seat = new Seat();
        seat.setSeatNumber("A1");

        when(seatService.getSeatsByShowtimeId(showtimeId)).thenReturn(List.of(seat));

        ResponseEntity<List<Seat>> response = seatController.getSeatsByShowtimeId(showtimeId);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().size());
        assertEquals("A1", response.getBody().get(0).getSeatNumber());
    }

    @Test
    public void testGetSeatByShowtimeIdAndSeatNumber() {
        UUID showtimeId = UUID.randomUUID();
        Seat seat = new Seat();
        seat.setSeatNumber("A1");

        when(seatService.getSeatByShowtimeIdAndSeatNumber(showtimeId, "A1")).thenReturn(Optional.of(seat));

        ResponseEntity<Seat> response = seatController.getSeatByShowtimeIdAndSeatNumber(showtimeId, "A1");

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("A1", response.getBody().getSeatNumber());
    }
}
