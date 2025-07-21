package com.petproject.walkadvisor.controller;

import com.petproject.walkadvisor.service.GeoService;
import com.petproject.walkadvisor.model.RouteData;
import com.petproject.walkadvisor.service.RouteService;
import com.petproject.walkadvisor.dto.RouteRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@Slf4j
@RestController
@RequestMapping("/api")
public class RouteController {
    
    private final RouteService routeService;

    private final GeoService geoService;

    public RouteController(RouteService routeService, GeoService geoService) {
        this.routeService = routeService;
        this.geoService = geoService;
    }

    @PostMapping("/routes")
    public CompletableFuture<ResponseEntity<RouteData>> geocode(@RequestBody RouteRequest request) {
        CompletableFuture<double[]> originFuture = geoService.geocode(request.getOrigin());
        CompletableFuture<double[]> destinationFuture = geoService.geocode(request.getDestination()); // Assuming a destination field

        return originFuture.thenCombineAsync(destinationFuture, this::formatCoordinates)
                /*.exceptionally(this::handleError)*/;
    }

    private ResponseEntity<RouteData> formatCoordinates(double[] latlonFrom, double[] latlonTo) {
        RouteData route = routeService.calculateRoute(latlonFrom[0], latlonFrom[1], latlonTo[0], latlonTo[1]);

        return ResponseEntity.ok(route);
    }
}
