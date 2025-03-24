package com.tdp.popcorn_palace;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.tdp.popcorn_palace.controller.MovieController;
import com.tdp.popcorn_palace.service.ShowtimeService;

@SpringBootTest
class PopcornPalaceApplicationTests {

    @Autowired
    private MovieController movieController;

    @Autowired
    private ShowtimeService showtimeService;

    @Test
    void contextLoads() {
        assertNotNull(movieController, "MovieController should be loaded in the context");
        assertNotNull(showtimeService, "ShowtimeService should be loaded in the context");
    }
}