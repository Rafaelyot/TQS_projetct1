package com.air_quality.backend.air_quality.temporal;


import com.air_quality.backend.air_quality.utils.features.AirCondition;
import com.air_quality.backend.utils.*;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

@Service
@Transactional
public class TemporalAirServiceImpl implements TemporalAirService {

    private Serializer serializer;

    public TemporalAirServiceImpl() {
        this.serializer = new Serializer();
    }

    public TemporalAirServiceImpl(Serializer serializer) {
        this.serializer = serializer;
    }


    @Override
    public TemporalAir getHistoryAirConditions(double latitude, double longitude, int numberHours) throws URISyntaxException, IOException, java.text.ParseException {
        List<AirCondition> history = serializer.serializeTemporal(latitude, longitude, numberHours);
        TemporalAir air = new TemporalAir(latitude, longitude, numberHours);
        air.setTemporalAirConditions(history);
        return air;
    }


    @Override
    public TemporalAir save(TemporalAir air) {
        return air;
    }
}
