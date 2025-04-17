package com.petproject.walkadvisor.dto;

public class RouteRequest {
    private String origin;
    private String destination;
    
    // Constructors
    public RouteRequest() {}
    
    public RouteRequest(String origin, String destination) {
        this.origin = origin;
        this.destination = destination;
    }
    
    // Getters and setters
    public String getOrigin() {
        return origin;
    }
    
    public void setOrigin(String origin) {
        this.origin = origin;
    }
    
    public String getDestination() {
        return destination;
    }
    
    public void setDestination(String destination) {
        this.destination = destination;
    }
}
