package com.air_quality.backend.air_quality;

import com.air_quality.backend.air_quality.current.Air;
import com.air_quality.backend.air_quality.current.AirService;
import com.air_quality.backend.air_quality.temporal.TemporalAir;
import com.air_quality.backend.air_quality.temporal.TemporalAirService;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.net.URISyntaxException;


@RestController
@RequestMapping("/air")
public class AirRestController {


    @Autowired
    private AirService airService;

    @Autowired
    private TemporalAirService temporalAirService;

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(path = "/lat={latitude}&long={longitude}", produces = "application/json")
    public Air getCurrentCondition(@PathVariable double latitude, @PathVariable double longitude) {

        try {
            return airService.getCurrentAirConditions(latitude, longitude);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage(), e);
        }
    }


    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(path = "/history/lat={latitude}&long={longitude}&hours={numberHours}", produces = "application/json")
    public TemporalAir getHistory(@PathVariable double latitude, @PathVariable double longitude, @PathVariable int numberHours) {
        try {
            return temporalAirService.getHistoryAirConditions(latitude, longitude, numberHours);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage(), e);
        }
    }


    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(path = "/by-date/lat={latitude}&long={longitude}&datetime={date}", produces = "application/json")
    public Air getAirByDate(@PathVariable double latitude, @PathVariable double longitude, @PathVariable long date) {
        try {
            return airService.getAirConditionByDate(latitude, longitude, date);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage(), e);
        }
    }


}
