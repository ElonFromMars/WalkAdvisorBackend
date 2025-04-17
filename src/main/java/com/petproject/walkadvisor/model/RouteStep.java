package com.petproject.walkadvisor.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class RouteStep {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String instruction;
    private Double distance;
    private Double duration;
    
    @ManyToOne
    @JoinColumn(name = "route_id")
    @JsonIgnore
    private RouteData route;
    
    // Constructors
    public RouteStep() {}
    
    public RouteStep(String instruction, Double distance, Double duration) {
        this.instruction = instruction;
        this.distance = distance;
        this.duration = duration;
    }
    
    // Getters and setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getInstruction() {
        return instruction;
    }
    
    public void setInstruction(String instruction) {
        this.instruction = instruction;
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
    
    public RouteData getRoute() {
        return route;
    }
    
    public void setRoute(RouteData route) {
        this.route = route;
    }
}
