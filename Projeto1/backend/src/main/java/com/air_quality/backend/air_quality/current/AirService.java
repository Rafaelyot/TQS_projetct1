package com.air_quality.backend.air_quality.current;

import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.URISyntaxException;


public interface AirService {

    public Air getCurrentAirConditions(double latitude, double longitude) throws URISyntaxException, IOException, ParseException, java.text.ParseException;

    public Air getAirConditionByDate(double latitude, double longitude, long date) throws IOException, URISyntaxException, java.text.ParseException;

    public Air save(Air air);
}
