package com.air_quality.backend.auto_complete;


import com.air_quality.backend.cache_statistics.StatisticsService;
import com.air_quality.backend.cache_statistics.StatisticsServiceImpl;
import com.air_quality.backend.utils.DateTools;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

@Service
@Transactional
@Primary
public class CityProxyServiceImpl implements CityService {

    @Autowired
    private CityRepository cityRepository;

    private CityService webService;

    private StatisticsService statisticsService;

    public CityProxyServiceImpl() {
        this.webService = new CityServiceImpl();
        this.statisticsService = new StatisticsServiceImpl();
    }

    @Override
    public List<City> getCityName(String name) throws ParseException, IOException, URISyntaxException {
        if (name.length() < 3)
            throw new IOException("O parametro 'name' deve ter pelo menos 3 caracteres");
        List<City> cities = cityRepository.getCityByName(name);
        if (cities.isEmpty()) {
            cities = this.saveAll(this.webService.getCityName(name));
            statisticsService.incrementMisses();
        } else {
            cityRepository.deleteCityByTimestampBefore(DateTools.getDateMinusMilliSeconds(City.TTL));
            statisticsService.incrementHits();
        }

        statisticsService.incrementRequests();
        return cities;
    }


    @Override
    public List<City> saveAll(List<City> cities) {
        return cityRepository.saveAll(cities);
    }


}
