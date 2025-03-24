package com.tdp.popcorn_palace.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
public class Customer {

    @Id
    @GeneratedValue
    private UUID id;

    private String name;

    private String email;

    private String phone;

    // private List<Booking> bookings;
}
