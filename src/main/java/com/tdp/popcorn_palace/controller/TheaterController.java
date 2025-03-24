package com.tdp.popcorn_palace.controller;

import com.tdp.popcorn_palace.model.Theater;
import com.tdp.popcorn_palace.service.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/theaters")
public class TheaterController {

    @Autowired
    private TheaterService theaterService;

    @GetMapping
    public List<Theater> getAllTheaters() {
        return theaterService.getAllTheaters();
    }

    @GetMapping("/{id}")
    public Theater getTheaterById(@PathVariable UUID id) {
        return theaterService.getTheaterById(id);
    }

    @PostMapping
    public Theater createTheater(@RequestBody Theater theater) {
        return theaterService.createTheater(theater);
    }

    @DeleteMapping("/{id}")
    public void deleteTheater(@PathVariable UUID id) {
        theaterService.deleteTheater(id);
    }
}
