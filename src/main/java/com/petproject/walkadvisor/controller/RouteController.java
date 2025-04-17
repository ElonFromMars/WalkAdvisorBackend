package com.petproject.walkadvisor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import com.petproject.walkadvisor.model.RouteData;
import com.petproject.walkadvisor.service.RouteService;
import com.petproject.walkadvisor.dto.RouteRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RouteController {
    
    @Autowired
    private RouteService routeService;
    
    @PostMapping("/routes")
    public ResponseEntity<RouteData> createRoute(@RequestBody RouteRequest request) {
        RouteData route = routeService.calculateRoute(request.getOrigin(), request.getDestination());
        return ResponseEntity.ok(route);
    }
}
