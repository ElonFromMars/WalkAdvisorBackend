package com.petproject.walkadvisor.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.petproject.walkadvisor.dto.GeocodingResponse;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.concurrent.CompletableFuture;

@Service
public class GeoService {

    private static final String PHOTON_API = "https://photon.komoot.io";

    @Async
    public CompletableFuture<double[]> geocode(String address) {

        WebClient webClient = WebClient.builder().baseUrl(PHOTON_API).build();
        String responseJson = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/api")
                        .queryParam("q", address)
                        .queryParam("limit", 1)
                        .build())
                .retrieve()
                .bodyToMono(String.class)
                .block();

        ObjectMapper mapper = new ObjectMapper();
        GeocodingResponse response = null;
        try {
            response = mapper.readValue(responseJson, GeocodingResponse.class);
        } catch (JsonProcessingException e) {
            //TODO
        }

        if (response != null && !response.features.isEmpty()) {
            var coords = response.features.get(0).geometry.coordinates;
            return CompletableFuture.completedFuture(new double[]{coords.get(1), coords.get(0)}); // [lat, lon]
        }

        return CompletableFuture.failedFuture(new RuntimeException("No coordinates found"));
    }
}