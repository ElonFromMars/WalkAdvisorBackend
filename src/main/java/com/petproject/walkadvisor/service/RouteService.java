package com.petproject.walkadvisor.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.petproject.walkadvisor.model.RouteData;
import com.petproject.walkadvisor.model.RouteStep;
import com.petproject.walkadvisor.repository.RouteRepository;

@Service
public class RouteService {
    
    @Autowired
    private RouteRepository routeRepository;
    
    public RouteData calculateRoute(String origin, String destination) {
        // In a real application, this would call a mapping service
        // For this example, we'll create mock data
        RouteData routeData = new RouteData(origin, destination);
        
        // Generate mock data for the route
        Random random = new Random();
        double totalDistance = 0.5 + random.nextDouble() * 5.0; // 0.5 to 5.5 km
        double totalDuration = totalDistance * 12; // Assuming 5 min per km
        
        routeData.setDistance(totalDistance);
        routeData.setDuration(totalDuration);
        routeData.setSafetyScore(3.5 + random.nextDouble() * 1.5); // 3.5 to 5.0 safety score
        
        // Create mock route steps
        List<RouteStep> steps = new ArrayList<>();
        
        RouteStep step1 = new RouteStep("Head north on Main St", 0.2, 2.5);
        step1.setRoute(routeData);
        steps.add(step1);
        
        RouteStep step2 = new RouteStep("Turn right onto Oak Ave", 0.1, 1.5);
        step2.setRoute(routeData);
        steps.add(step2);
        
        RouteStep step3 = new RouteStep("Continue straight onto Park Rd", 0.3, 4.0);
        step3.setRoute(routeData);
        steps.add(step3);
        
        routeData.setSteps(steps);
        
        // Save to database
        return routeRepository.save(routeData);
    }
}
