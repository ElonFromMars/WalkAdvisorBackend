package com.petproject.walkadvisor.integration.services;

import com.petproject.walkadvisor.service.GeoService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

@WebMvcTest(GeoService.class)
public class GeoServiceTest {
    private static final Logger log = LoggerFactory.getLogger(GeoServiceTest.class);

    @Test
    void testGeocodingAPI() {
        var geoService = new GeoService();
        var helcowCoordinates = geoService.geocode("Helclów 9 Kraków").join();
        var krakowGlownyCoordinates = geoService.geocode("Kraków Główny").join();

        assertEquals(2, helcowCoordinates.length);
        assertEquals(2, krakowGlownyCoordinates.length);

        var expectedHelcowCoordinates = new double[] { 50.0722367, 19.937055 };
        var expectedKrakowGlownyCoordinates = new double[] { 50.0686663, 19.947802 };
        var permissibleCoordinateError = 0.001;
        assertEquals(expectedHelcowCoordinates[0], helcowCoordinates[0], permissibleCoordinateError);
        assertEquals(expectedHelcowCoordinates[1], helcowCoordinates[1], permissibleCoordinateError);
        assertEquals(expectedKrakowGlownyCoordinates[0], krakowGlownyCoordinates[0], permissibleCoordinateError);
        assertEquals(expectedKrakowGlownyCoordinates[1], krakowGlownyCoordinates[1], permissibleCoordinateError);

        log.info(Arrays.toString(helcowCoordinates));
        log.info(Arrays.toString(krakowGlownyCoordinates));
    }
}
