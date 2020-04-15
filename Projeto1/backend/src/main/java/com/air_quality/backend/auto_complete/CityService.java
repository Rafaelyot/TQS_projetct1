package com.air_quality.backend.auto_complete;

import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public interface CityService {

    public List<City> getCityName(String name) throws IOException, URISyntaxException, ParseException;

    public List<City> saveAll(List<City> cities);


}
