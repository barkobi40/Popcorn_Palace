package com.tdp.popcorn_palace.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ShowtimeDTO {
    private Long movieId;
    private String theater;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private double price;
}
