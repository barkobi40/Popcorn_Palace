package com.tdp.popcorn_palace.controller;

import com.tdp.popcorn_palace.model.Showtime;
import com.tdp.popcorn_palace.service.ShowtimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/showtimes")
public class ShowtimeController {

    @Autowired
    private ShowtimeService showtimeService;

    @PostMapping
    public Showtime addShowtime(
            @RequestParam Long movieId,
            @RequestParam String theater,
            @RequestParam String startTime,
            @RequestParam String endTime,
            @RequestParam double price
    ) {
        return showtimeService.addShowtime(
            movieId,
            theater,
            LocalDateTime.parse(startTime),
            LocalDateTime.parse(endTime),
            price
        );
    }

    @GetMapping
    public List<Showtime> getAllShowtimes() {
        return showtimeService.getAllShowtimes();
    }

    @GetMapping("/{id}")
    public Showtime getShowtime(@PathVariable Long id) {
        return showtimeService.getShowtimeById(id);
    }

    @PutMapping("/{id}")
    public Showtime updateShowtime(
            @PathVariable Long id,
            @RequestParam String startTime,
            @RequestParam String endTime,
            @RequestParam double price
    ) {
        return showtimeService.updateShowtime(
            id,
            LocalDateTime.parse(startTime),
            LocalDateTime.parse(endTime),
            price
        );
    }

    @DeleteMapping("/{id}")
    public void deleteShowtime(@PathVariable Long id) {
        showtimeService.deleteShowtime(id);
    }
}
