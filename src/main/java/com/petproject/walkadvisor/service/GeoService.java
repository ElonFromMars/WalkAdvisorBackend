package com.petproject.walkadvisor.service;

import com.petproject.walkadvisor.dto.GeocodingResponse;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.concurrent.CompletableFuture;

@Service
public class GeoService {

    private static final String PHOTON_API = "https://photon.komoot.io/api";
    private final RestTemplate restTemplate = new RestTemplate();

    @Async
    public CompletableFuture<double[]> geocode(String address) {
        String url = UriComponentsBuilder.fromHttpUrl(PHOTON_API)
                .queryParam("q", address)
                .queryParam("limit", 1)
                .toUriString();

        GeocodingResponse response = restTemplate.getForObject(url, GeocodingResponse.class);

        if (response != null && !response.features.isEmpty()) {
            var coords = response.features.get(0).geometry.coordinates;
            return CompletableFuture.completedFuture(new double[]{coords.get(1), coords.get(0)}); // [lat, lon]
        }

        return CompletableFuture.failedFuture(new RuntimeException("No coordinates found"));
    }
}