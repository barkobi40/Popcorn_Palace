package com.tdp.popcorn_palace.service;

import com.tdp.popcorn_palace.model.Theater;
import com.tdp.popcorn_palace.repository.TheaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TheaterService {

    @Autowired
    private TheaterRepository theaterRepository;

    public List<Theater> getAllTheaters() {
        return theaterRepository.findAll();
    }

    public Theater getTheaterById(UUID id) {
        return theaterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Theater not found"));
    }

    public Theater createTheater(Theater theater) {
        if (theaterRepository.existsByName(theater.getName())) {
            throw new RuntimeException("Theater name already exists");
        }
        return theaterRepository.save(theater);
    }

    public void deleteTheater(UUID id) {
        theaterRepository.deleteById(id);
    }
}
