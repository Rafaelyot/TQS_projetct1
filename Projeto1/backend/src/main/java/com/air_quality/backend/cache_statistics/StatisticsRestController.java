package com.air_quality.backend.cache_statistics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;import org.springframework.http.HttpStatus;
@RestController
@RequestMapping("/statistics")
public class StatisticsRestController {

    @Autowired
    private StatisticsService statisticsService;


    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(produces = "application/json")
    public Statistics getStatistics() {
        try {
            return statisticsService.getStatistics();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage(), e);
        }
    }
}
