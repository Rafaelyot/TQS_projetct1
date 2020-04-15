package com.air_quality.backend.air_quality.temporal;

import com.air_quality.backend.air_quality.utils.features.AirCondition;
import com.air_quality.backend.cache_statistics.StatisticsService;
import com.air_quality.backend.utils.DateTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.net.URISyntaxException;

import java.text.ParseException;
import java.util.ArrayList;

import java.util.List;

@Service
@Transactional
@Primary
public class TemporalAirProxyServiceImpl implements TemporalAirService {

    @Autowired
    private TemporalAirRepository repository;


    private TemporalAirService webService;

    @Autowired
    private StatisticsService statisticsService;

    public TemporalAirProxyServiceImpl() {
        this.webService = new TemporalAirServiceImpl();
    }


    @Override
    public TemporalAir getHistoryAirConditions(double latitude, double longitude, int numberHours) throws IOException, URISyntaxException, ParseException, CloneNotSupportedException {
        TemporalAir airReal = repository.getTemporalAirByLatitudeAndLongitude(latitude, longitude);
        TemporalAir air = (TemporalAir) airReal.clone();

        if (air == null) { // First time querying

            if (numberHours > 0) { // Needed because of error caused by 0 hours arg in breezometter API
                air = this.save(this.webService.getHistoryAirConditions(latitude, longitude, numberHours));

            } else {
                air = new TemporalAir(latitude, longitude, numberHours);
                air.setTemporalAirConditions(new ArrayList<>());
            }
            statisticsService.incrementMisses();
        } else {

            if (numberHours <= 0)  // If no hours requested
                air.setTemporalAirConditions(new ArrayList<>());

            else if (air.getTemporalAirConditions().size() < numberHours * 2) { // If data in cache had less values than number of hours requested
                this.repository.delete(air);
                air = this.save(this.webService.getHistoryAirConditions(latitude, longitude, numberHours));


            } else if (air.getTemporalAirConditions().size() > numberHours * 2) { // If data in cache had more values than number of hours requested
                List<AirCondition> history = air.getTemporalAirConditions();
                int historySize = air.getTemporalAirConditions().size();
                int hoursDifference = (historySize / 2) - numberHours;
                air.setTemporalAirConditions(history.subList(hoursDifference, historySize - hoursDifference));

            }

            repository.deleteTemporalAirByTimestampBefore(DateTools.getDateMinusMilliSeconds(TemporalAir.TTL));
            statisticsService.incrementHits();
        }

        statisticsService.incrementRequests();

        return air;
    }


    @Override
    public TemporalAir save(TemporalAir air) {

        return this.repository.save(air);
    }
}
