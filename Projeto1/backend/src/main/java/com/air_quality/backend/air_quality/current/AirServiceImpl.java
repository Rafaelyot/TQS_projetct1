package com.air_quality.backend.air_quality.current;

import com.air_quality.backend.air_quality.utils.Utils;
import com.air_quality.backend.air_quality.utils.features.AirCondition;
import com.air_quality.backend.utils.DateTools;
import com.air_quality.backend.utils.Serializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;

@Service
@Transactional
public class AirServiceImpl implements AirService {

    private Serializer serializer;


    public AirServiceImpl() {
        this.serializer = new Serializer();
    }

    public AirServiceImpl(Serializer serializer) {
        this.serializer = serializer;
    }


    @Override
    public Air getCurrentAirConditions(double latitude, double longitude) throws URISyntaxException, IOException, java.text.ParseException {
        AirCondition airCondition = serializer.serializeCurrent(latitude, longitude);
        Air air = new Air(latitude, longitude, DateTools.getCurrentDate());
        air.setCurrentAirCondition(airCondition);
        return air;
    }

    @Override
    public Air getAirConditionByDate(double latitude, double longitude, long dateSeconds) throws IOException, URISyntaxException, ParseException {
        String type = Utils.getType(dateSeconds);

        AirCondition airCondition = serializer.serializeAirByDate(latitude, longitude, DateTools.convertSecondsToBMDate(dateSeconds), type);
        Air air = new Air(latitude, longitude, DateTools.convertSecondsToDate(dateSeconds));
        air.setCurrentAirCondition(airCondition);
        return air;
    }


    @Override
    public Air save(Air air) {
        return air;
    }
}
