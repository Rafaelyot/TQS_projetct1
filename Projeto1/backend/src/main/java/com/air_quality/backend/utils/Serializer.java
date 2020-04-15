package com.air_quality.backend.utils;

import com.air_quality.backend.air_quality.utils.features.AirCondition;
import com.air_quality.backend.air_quality.utils.features.AirPollutant;
import com.air_quality.backend.auto_complete.City;
import com.air_quality.backend.utils.cities_search_serializers.CitySearch;
import com.air_quality.backend.utils.cities_search_serializers.CitySearchResult;
import com.air_quality.backend.utils.breezometer_serializers.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.*;

public class Serializer {

    // api.teleport constants
    private static final String TELEPORT_URL = "https://api.teleport.org/api/cities/";

    // BreezoMetter constants
    private static final String BMURL = "https://api.breezometer.com/air-quality/v2/";
    private static final String KEY = "afb0f2b8423e4ef8bc92b2b62f9e1528";
    private static final String FEATURES = "breezometer_aqi,health_recommendations,sources_and_effects,dominant_pollutant_concentrations,pollutants_concentrations,pollutants_aqi_information";

    private final ObjectMapper objectMapper = new ObjectMapper();
    private RequestTools requestTools;

    public Serializer() {
        this.requestTools = RequestTools.getInstance();
    }

    public Serializer(RequestTools requestTools) {
        this.requestTools = requestTools;
    }

    // For BreezoMetter data
    public AirCondition serializeCurrent(double latitude, double longitude) throws URISyntaxException, IOException, ParseException {
        Map<String, String> urlParams = defaultParams(latitude, longitude);

        String response = requestTools.makeRequest(BMURL + "current-conditions", urlParams);
        BMCurrentAirConditions c = objectMapper.readValue(response, BMCurrentAirConditions.class);
        BMData data = c.getData();

        return getAirConditionFromData(data);
    }

    public List<AirCondition> serializeTemporal(double latitude, double longitude, int numberHours) throws URISyntaxException, IOException, ParseException {
        Map<String, String> urlParams = defaultParams(latitude, longitude);
        urlParams.put("hours", String.valueOf(numberHours));
        List<AirCondition> history = new ArrayList<>();

        String[] types = new String[]{"historical", "forecast"};
        for (String type : types) {
            String response = requestTools.makeRequest(BMURL + type + "/hourly", urlParams);

            BMListAirConditions listAirConditions = this.objectMapper.readValue(response, BMListAirConditions.class);
            List<BMData> listData = listAirConditions.getData();


            for (BMData data : listData)
                history.add(getAirConditionFromData(data));
        }
        history.sort(new AirConditionComparator());
        return history;
    }

    // For api.teleport data

    public List<City> serializeCities(String name) throws IOException, URISyntaxException, org.json.simple.parser.ParseException {
        Map<String, String> urlParams = new HashMap<>();
        urlParams.put("search", name);

        String response = requestTools.makeRequest(TELEPORT_URL, urlParams);

        CitySearch citySearch = this.objectMapper.readValue(response, CitySearch.class);

        List<City> cities = new ArrayList<>();
        for (CitySearchResult city : citySearch.getEmbedded().getCitySearchResults()) {
            String fullName = city.getMatchingFullName();
            String coordinatesHref = city.getHref();
            String[] parts = coordinatesHref.replace("/", "").split("geonameid:");
            long id = Long.parseLong(parts[parts.length - 1]);

            JSONObject coordinatesResponse = (JSONObject) new JSONParser().parse(requestTools.get(coordinatesHref)); // Overkill to had an serializer to get a single field
            JSONObject location = (JSONObject) ((JSONObject) coordinatesResponse.get("location")).get("latlon");
            double latitude = (double) location.get("latitude");
            double longitude = (double) location.get("longitude");

            cities.add(new City(id, name, fullName, latitude, longitude));
        }

        return cities;
    }

    public AirCondition serializeAirByDate(double latitude, double longitude, String date, String type) throws IOException, URISyntaxException, ParseException {
        Map<String, String> urlParams = defaultParams(latitude, longitude);
        urlParams.put("datetime", date);

        String response = requestTools.makeRequest(BMURL + type + "/hourly", urlParams);

        BMCurrentAirConditions c = objectMapper.readValue(response, BMCurrentAirConditions.class);
        BMData data = c.getData();

        return getAirConditionFromData(data);
    }


    // Useful functions
    private Map<String, String> defaultParams(double latitude, double longitude) {
        Map<String, String> params = new HashMap<>();
        params.put("features", FEATURES);
        params.put("lat", String.valueOf(latitude));
        params.put("lon", String.valueOf(longitude));
        params.put("key", KEY);
        return params;
    }

    private AirCondition getAirConditionFromData(BMData data) throws ParseException {
        BMBaqi baqi = data.getIndexes().getBaqi();

        Date datetime = DateTools.parse(data.getDatetime());
        long airScore = baqi.getAqi();
        String category = baqi.getCategory();
        String color = baqi.getColor();

        BMPollutant pollutant = data.getPollutants().getPollutantByName(baqi.getDominantPollutant());

        return new AirCondition(datetime, airScore, category, color,
                new AirPollutant(pollutant.getFullName(), pollutant.getConcentration().getValue(),
                        pollutant.getConcentration().getUnits()), data.getPollutants(), data.getHealthRecommendations());

    }


}
