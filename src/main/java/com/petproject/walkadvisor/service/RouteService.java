package com.petproject.walkadvisor.service;

import com.graphhopper.ResponsePath;
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

    @Autowired
    private GraphHopperService graphHopperService;
    
    public RouteData calculateRoute(String origin, String destination) {
        double fromLat = 50.0647;
        double fromLon = 19.9450;
        double toLat = 50.0675;
        double toLon = 19.9125;

        ResponsePath path = graphHopperService.getRoute(fromLat, fromLon, toLat, toLon);

        RouteData routeData = new RouteData("Kraków Point A", "Kraków Point B");
        routeData.setDistance(path.getDistance() / 1000.0); // km
        routeData.setDuration(path.getTime() / 60000.0); // minutes
        routeData.setSafetyScore(4.0); // TODO: Add real safety metric later

        List<RouteStep> steps = new ArrayList<>();
        path.getInstructions().forEach(instruction -> {
            RouteStep step = new RouteStep(
                    instruction.getTurnDescription(null),
                    instruction.getDistance() / 1000.0,
                    instruction.getTime() / 60000.0
            );
            step.setRoute(routeData);
            steps.add(step);
        });

        routeData.setSteps(steps);
        return routeRepository.save(routeData);
    }
}
