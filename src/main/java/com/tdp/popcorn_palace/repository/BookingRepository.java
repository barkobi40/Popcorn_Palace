package com.tdp.popcorn_palace.repository;

import com.tdp.popcorn_palace.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}
