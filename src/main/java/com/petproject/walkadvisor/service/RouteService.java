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
    
    public RouteData calculateRoute(double fromLat, double fromLon, double toLat, double toLon) {
        //TODO Point origin, Point destination

        ResponsePath path = graphHopperService.getRoute(fromLat, fromLon, toLat, toLon);

        RouteData routeData = new RouteData("Kraków Point A", "Kraków Point B");
        routeData.setDistance(path.getDistance() / 1000.0); // km
        routeData.setDuration(path.getTime() / 60000.0); // minutes
        routeData.setSafetyScore(4.0); // TODO: Add real safety metric later

        List<RouteStep> steps = new ArrayList<>();
        path.getInstructions().forEach(instruction -> {
            RouteStep step = new RouteStep(
                    instruction.getName(),
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
