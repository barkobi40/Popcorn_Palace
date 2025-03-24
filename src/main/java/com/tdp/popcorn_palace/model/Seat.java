package com.tdp.popcorn_palace.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "showtime_id")
    private Showtime showtime;


    @Column(name = "seat_number")
    private String seatNumber;

    @Column
    private boolean booked;

    @Column(name = "customer_id")
    private String customerId;
}
