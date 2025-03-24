package com.tdp.popcorn_palace.dto;

import lombok.Data;

@Data
public class BookingDTO {
    private Long id;
    private Long showtimeId;
    private Long customerId;
    private int seatNumber;
}