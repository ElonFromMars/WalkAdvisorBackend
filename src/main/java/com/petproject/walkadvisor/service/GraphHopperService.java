package com.petproject.walkadvisor.service;

import com.graphhopper.GHRequest;
import com.graphhopper.GHResponse;
import com.graphhopper.GraphHopper;
import com.graphhopper.ResponsePath;
import com.graphhopper.config.CHProfile;
import com.graphhopper.config.Profile;
import com.graphhopper.util.*;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class GraphHopperService {
    public ResponsePath getRoute(double latFrom, double lonFrom, double latTo, double lonTo) {
        GraphHopper hopper = new GraphHopper();
        String ghLoc = "src/main/resources/graphhopper/krakow-gh/malopolskie-latest.osm.pbf";
        hopper.setOSMFile(ghLoc);
        hopper.setGraphHopperLocation("target/routing-graph-cache");

        // add all encoded values that are used in the custom model, these are also available as path details or for client-side custom models
        hopper.setEncodedValuesString("car_access, car_average_speed, road_access");
        // see docs/core/profiles.md to learn more about profiles
        hopper.setProfiles(new Profile("car").setCustomModel(GHUtility.loadCustomModelFromJar("car.json")));
        //hopper.setProfiles(new Profile("car").setCustomModel(GHUtility.loadCustomModelFromJar("car.json")));

        // this enables speed mode for the profile we called car
        //hopper.getCHPreparationHandler().setCHProfiles(new CHProfile("car").);
        hopper.importOrLoad();

        // ... but for the hybrid mode we can customize the route calculation even at request time:
        // 1. a request with default preferences
        GHRequest req = new GHRequest(latFrom, lonFrom, latTo, lonTo).
                // note that we have to specify which profile we are using even when there is only one like here
                        setProfile("car").
                // define the language for the turn instructions
                        setLocale(Locale.US);
        GHResponse rsp = hopper.route(req);

        // handle errors
        if (rsp.hasErrors())
            throw new RuntimeException(rsp.getErrors().toString());

        // use the best path, see the GHResponse class for more possibilities.
        ResponsePath path = rsp.getBest();

        // points, distance in meters and time in millis of the full path
        PointList pointList = path.getPoints();
        double distance = path.getDistance();
        long timeInMs = path.getTime();

        Translation tr = hopper.getTranslationMap().getWithFallBack(Locale.UK);
        InstructionList il = path.getInstructions();
        // iterate over all turn instructions
        for (Instruction instruction : il) {
            // System.out.println("distance " + instruction.getDistance() + " for instruction: " + instruction.getTurnDescription(tr));
        }

        return path;
    }
}