package com.tdp.popcorn_palace.model;

import jakarta.persistence.*;
import lombok.*; // <-- חשוב!

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int seatNumber;
    private boolean booked;
    private String customerId;

    @ManyToOne
    @JoinColumn(name = "showtime_id")
    private Showtime showtime;
}
