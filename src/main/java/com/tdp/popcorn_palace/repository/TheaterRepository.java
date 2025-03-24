package com.tdp.popcorn_palace.repository;

import com.tdp.popcorn_palace.model.Theater;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface TheaterRepository extends JpaRepository<Theater, UUID> {
    boolean existsByName(String name);
}
