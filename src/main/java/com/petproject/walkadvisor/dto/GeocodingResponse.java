package com.petproject.walkadvisor.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GeocodingResponse {
    public List<Feature> features;

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Feature {
        public Geometry geometry;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Geometry {
        public List<Double> coordinates;
    }
}
