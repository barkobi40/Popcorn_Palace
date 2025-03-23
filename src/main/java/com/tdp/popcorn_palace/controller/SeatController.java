package com.tdp.popcorn_palace.controller;

import com.tdp.popcorn_palace.model.Seat;
import com.tdp.popcorn_palace.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seats")
public class SeatController {

    @Autowired
    private SeatService seatService;

    @PostMapping("/showtime/{showtimeId}")
    public Seat createSeat(@PathVariable Long showtimeId, @RequestParam int seatNumber) {
        return seatService.createSeat(showtimeId, seatNumber);
    }

    @GetMapping("/showtime/{showtimeId}")
    public List<Seat> getSeats(@PathVariable Long showtimeId) {
        return seatService.getSeatsByShowtime(showtimeId);
    }

    @PutMapping("/{seatId}/book")
    public Seat bookSeat(@PathVariable Long seatId, @RequestParam String customerId) {
        return seatService.bookSeat(seatId, customerId);
    }

    @PutMapping("/{seatId}/cancel")
    public Seat cancelSeat(@PathVariable Long seatId) {
        return seatService.cancelSeat(seatId);
    }
}
