package com.air_quality.backend.air_quality.temporal;


import java.io.IOException;
import java.net.URISyntaxException;




public interface TemporalAirService {

    public TemporalAir getHistoryAirConditions(double latitude, double longitude, int numberHours) throws URISyntaxException, IOException, java.text.ParseException, CloneNotSupportedException;

    public TemporalAir save(TemporalAir air);
}
