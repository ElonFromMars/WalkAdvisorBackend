package com.petproject.walkadvisor.unit.services;

import com.petproject.walkadvisor.model.RouteStep;
import com.petproject.walkadvisor.service.GraphHopperService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.both;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThan;


@WebMvcTest(GraphHopperService.class)
public class GraphHopperServiceTest {
    private static final Logger log = LoggerFactory.getLogger(GraphHopperServiceTest.class);

    @Test
    void testGetRoute() {
        var graphHopperService = new GraphHopperService();

        var helcowCoordinates =       new double[] { 50.0722367, 19.937055 };
        var krakowGlownyCoordinates = new double[] { 50.0635388, 19.937468 };

        var route = graphHopperService.getRoute(helcowCoordinates[0], helcowCoordinates[1], krakowGlownyCoordinates[0], krakowGlownyCoordinates[1]);

        assertThat(route.getInstructions().size(), is(both(greaterThan(2)).and(lessThan(100))));

        log.info("Route: ");
        var instructions = route.getInstructions();
        instructions.forEach(instruction -> {
            log.info(instruction.toString());
        });
        var points = route.getWaypoints();
        points.forEach(point -> {
            log.info(point.toString());
        });
        log.info(String.valueOf(route.getInstructions().size()));
    }
}
