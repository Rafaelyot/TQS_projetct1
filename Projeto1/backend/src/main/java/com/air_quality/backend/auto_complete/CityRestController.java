package com.air_quality.backend.auto_complete;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/cities")
public class CityRestController {

    @Autowired
    private CityService cityService;

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(path = "/{name}", produces = "application/json")
    public List<City> getCityName(@PathVariable String name)  {
        try {
            return cityService.getCityName(name);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage(), e);
        }
    }


}
