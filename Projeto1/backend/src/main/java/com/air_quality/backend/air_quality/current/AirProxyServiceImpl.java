package com.air_quality.backend.air_quality.current;

import com.air_quality.backend.cache_statistics.StatisticsService;
import com.air_quality.backend.utils.DateTools;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.net.URISyntaxException;

@Service
@Transactional
@Primary
public class AirProxyServiceImpl implements AirService {


    @Autowired
    private AirRepository repository;

    private AirService webService;

    @Autowired
    private StatisticsService statisticsService;

    public AirProxyServiceImpl() {
        this.webService = new AirServiceImpl();
    }

    @Override
    public Air getCurrentAirConditions(double latitude, double longitude) throws ParseException, IOException, URISyntaxException, java.text.ParseException {
        Air air = repository.getAirByLatitudeAndLongitudeAndDate(latitude, longitude, DateTools.getCurrentDate());
        if (air == null) {
            air = this.save(this.webService.getCurrentAirConditions(latitude, longitude));
            statisticsService.incrementMisses();
        } else {
            repository.deleteAirByTimestampBefore(DateTools.getDateMinusMilliSeconds(Air.TTL));
            statisticsService.incrementHits();
        }

        statisticsService.incrementRequests();
        return air;
    }

    @Override
    public Air getAirConditionByDate(double latitude, double longitude, long dateSeconds) throws IOException, URISyntaxException, java.text.ParseException {
        Air air = repository.getAirByLatitudeAndLongitudeAndDate(latitude, longitude, DateTools.convertSecondsToDate(dateSeconds));
        if (air == null) {
            air = this.save(this.webService.getAirConditionByDate(latitude, longitude, dateSeconds));
            statisticsService.incrementMisses();
        } else{
            repository.deleteAirByTimestampBefore(DateTools.getDateMinusMilliSeconds(Air.TTL));
            statisticsService.incrementHits();
        }


        return air;
    }


    @Override
    public Air save(Air air) {
        return this.repository.save(air);
    }
}
