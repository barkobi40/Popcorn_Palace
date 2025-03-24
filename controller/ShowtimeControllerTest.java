package com.tdp.popcorn_palace.controller;

import com.tdp.popcorn_palace.model.Showtime;
import com.tdp.popcorn_palace.service.ShowtimeService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ShowtimeControllerTest {

    @Mock
    private ShowtimeService showtimeService;

    @InjectMocks
    private ShowtimeController showtimeController;

    @Test
    public void testGetShowtimeById() {
        UUID id = UUID.randomUUID();
        Showtime showtime = new Showtime();
        showtime.setId(id);

        when(showtimeService.getShowtimeById(id.toString())).thenReturn(showtime);

        ResponseEntity<Showtime> response = showtimeController.getShowtimeById(id.toString());

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(id, response.getBody().getId());
    }

    @Test
    public void testDeleteShowtime() {
        UUID id = UUID.randomUUID();
        doNothing().when(showtimeService).deleteShowtime(id.toString());

        ResponseEntity<Void> response = showtimeController.deleteShowtime(id.toString());

        assertEquals(204, response.getStatusCodeValue());
    }
}
