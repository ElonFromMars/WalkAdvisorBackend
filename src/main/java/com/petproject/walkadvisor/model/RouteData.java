package com.petproject.walkadvisor.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class RouteData {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String origin;
    private String destination;
    private Double distance;
    private Double duration;
    private Double safetyScore;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "route")
    private List<RouteStep> steps;
    
    // Constructors
    public RouteData() {}
    
    public RouteData(String origin, String destination) {
        this.origin = origin;
        this.destination = destination;
    }
    
    // Getters and setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
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
    
    public Double getDistance() {
        return distance;
    }
    
    public void setDistance(Double distance) {
        this.distance = distance;
    }
    
    public Double getDuration() {
        return duration;
    }
    
    public void setDuration(Double duration) {
        this.duration = duration;
    }
    
    public Double getSafetyScore() {
        return safetyScore;
    }
    
    public void setSafetyScore(Double safetyScore) {
        this.safetyScore = safetyScore;
    }
    
    public List<RouteStep> getSteps() {
        return steps;
    }
    
    public void setSteps(List<RouteStep> steps) {
        this.steps = steps;
    }
}
