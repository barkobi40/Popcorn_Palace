package com.tdp.popcorn_palace.controller;

import com.tdp.popcorn_palace.model.Seat;
import com.tdp.popcorn_palace.service.SeatService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/seats")
public class SeatController {

    private final SeatService seatService;

    public SeatController(SeatService seatService) {
        this.seatService = seatService;
    }

    @GetMapping("/showtime/{showtimeId}")
    public List<Seat> getSeatsByShowtime(@PathVariable String showtimeId) {
        return seatService.getSeatsByShowtime(showtimeId);
    }

    @PostMapping("/{showtimeId}/{seatNumber}")
    public Seat createSeat(@PathVariable String showtimeId, @PathVariable String seatNumber) {
        return seatService.createSeat(showtimeId, seatNumber);
    }

    @PostMapping("/book/{seatId}/{customerId}")
    public Seat bookSeat(@PathVariable String seatId, @PathVariable String customerId) {
        return seatService.bookSeat(seatId, customerId);
    }

    @PostMapping("/cancel/{seatId}")
    public Seat cancelSeat(@PathVariable String seatId) {
        return seatService.cancelSeat(seatId);
    }
}
