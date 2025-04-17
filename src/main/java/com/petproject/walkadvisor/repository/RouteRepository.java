package com.petproject.walkadvisor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.petproject.walkadvisor.model.RouteData;

@Repository
public interface RouteRepository extends JpaRepository<RouteData, Long> {
    // Additional custom query methods can be added here if needed
}
