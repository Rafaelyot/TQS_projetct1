package com.air_quality.backend.auto_complete;

import java.net.URISyntaxException;

import java.util.List;
import java.io.IOException;

import com.air_quality.backend.utils.Serializer;

import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
@Transactional
public class CityServiceImpl implements CityService {

    private Serializer serializer;

    public CityServiceImpl(Serializer serializer) {
        this.serializer = serializer;
    }

    public CityServiceImpl() {
        this.serializer = new Serializer();
    }


    @Override
    public List<City> getCityName(String name) throws IOException, URISyntaxException, ParseException {
        return serializer.serializeCities(name);
    }

    @Override
    public List<City> saveAll(List<City> cities) {

        return cities;
    }


}
