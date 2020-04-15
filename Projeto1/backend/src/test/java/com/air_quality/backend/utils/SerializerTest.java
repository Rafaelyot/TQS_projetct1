package com.air_quality.backend.utils;

import com.air_quality.backend.TestUtils;
import com.air_quality.backend.air_quality.utils.features.AirCondition;
import com.air_quality.backend.air_quality.utils.features.AirPollutant;
import com.air_quality.backend.auto_complete.City;
import com.air_quality.backend.utils.breezometer_serializers.BMHealthRecommendations;
import com.air_quality.backend.utils.breezometer_serializers.BMPollutants;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.*;

import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Mockito.when;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@ExtendWith(MockitoExtension.class)
class SerializerTest {

    @Mock
    private RequestTools requestTools;

    @InjectMocks
    private Serializer serializer;

    private final static double latitude = 39.91177;
    private final static double longitude = -8.43568;
    private final static int numberHours = 2;
    private static final String TELEPORT_URL = "https://api.teleport.org/api/cities/";
    private static final String CITY_DETAILS_URL = "https://api.teleport.org/api/cities/geonameid:2271650/";
    private static final String BMURL = "https://api.breezometer.com/air-quality/v2/";
    private static final String KEY = "afb0f2b8423e4ef8bc92b2b62f9e1528";
    private static final String FEATURES = "breezometer_aqi,health_recommendations,sources_and_effects,dominant_pollutant_concentrations,pollutants_concentrations,pollutants_aqi_information";
    private static final String cityName = "ansiao";

    private Map<String, String> defaultParams(double latitude, double longitude) {
        Map<String, String> params = new HashMap<>();
        params.put("features", FEATURES);
        params.put("lat", String.valueOf(latitude));
        params.put("lon", String.valueOf(longitude));
        params.put("key", KEY);
        return params;
    }

    public AirCondition airConditionSetter() {
        Date date = new GregorianCalendar(2020, Calendar.APRIL, 7, 8, 0).getTime();
        long airScore = 73;
        String category = "Good air quality";
        String color = "#76CA33";
        AirPollutant airPollutant = new AirPollutant();
        BMPollutants pollutants = new BMPollutants();
        BMHealthRecommendations bmHealthRecommendations = new BMHealthRecommendations();
        return new AirCondition(date, airScore, category, color, airPollutant, pollutants, bmHealthRecommendations);
    }

    @Test
    void testConstructor() {
        Serializer serializerC = new Serializer();
        assertThat(serializerC, is(notNullValue()));
    }

    @Test
    void testSerializeCurrent() throws Exception {
        when(requestTools.makeRequest(BMURL + "current-conditions", defaultParams(latitude, longitude))).thenReturn(TestUtils.readJsonAsString("src/test/java/com/air_quality/backend/json_files/SerializerTestCurrentConditionsJson.json"));

        AirCondition result = serializer.serializeCurrent(latitude, longitude); // More important here is that does not thrown an errors
        AirCondition expected = this.airConditionSetter();

        // Some sanity check (check full object ??)
        assertThat(result.getColor(), is(expected.getColor()));
        assertThat(result.getCategory(), is(expected.getCategory()));
        assertThat(result.getAirScore(), is(expected.getAirScore()));
    }


    @Test
    void testSerializeTemporal() throws Exception {
        Map<String, String> urlParams = defaultParams(latitude, longitude);
        urlParams.put("hours", String.valueOf(numberHours));

        when(requestTools.makeRequest(BMURL + "historical/hourly", urlParams)).thenReturn(TestUtils.readJsonAsString("src/test/java/com/air_quality/backend/json_files/SerializerTestHistoricalConditionsJson.json"));
        when(requestTools.makeRequest(BMURL + "forecast/hourly", urlParams)).thenReturn(TestUtils.readJsonAsString("src/test/java/com/air_quality/backend/json_files/SerializerTestForecastConditionsJson.json"));

        List<AirCondition> result = serializer.serializeTemporal(latitude, longitude, numberHours); // More important here is that does not thrown an errors

        // Some sanity check (check full object ??)
        assertThat(result.size(), is(numberHours * 2));
    }


    @Test
    void testSerializeCities() throws Exception {
        Map<String, String> urlParams = new HashMap<>();
        urlParams.put("search", cityName);

        when(requestTools.makeRequest(TELEPORT_URL, urlParams)).thenReturn(TestUtils.readJsonAsString("src/test/java/com/air_quality/backend/json_files/SerializerTestCitiesJson.json"));
        when(requestTools.get(CITY_DETAILS_URL)).thenReturn(TestUtils.readJsonAsString("src/test/java/com/air_quality/backend/json_files/SerializerTestCityDetailsJson.json"));

        List<City> results = serializer.serializeCities(cityName); // More important here is that does not thrown an errors

        // Some sanity check
        assertThat(results.size(), is(1));
        assertThat(results.get(0).getId(), is(2271650L));
        assertThat(results.get(0).getLatitude(), is(latitude));
        assertThat(results.get(0).getLongitude(), is(longitude));
    }

    @Test
    void testSerializeAirByDate() throws IOException, URISyntaxException, ParseException {
        Map<String, String> urlParams = defaultParams(latitude, longitude);
        String date = "2020-04-13T23:00:00";
        urlParams.put("datetime", date);
        String type = "historical";

        when(requestTools.makeRequest(BMURL + type + "/hourly", urlParams)).thenReturn(TestUtils.readJsonAsString("src/test/java/com/air_quality/backend/json_files/SerializerTestAirByDate.json"));

        AirCondition airCondition = serializer.serializeAirByDate(latitude, longitude, date, type); // More important here is that does not thrown an errors

        // Some sanity check
        assertThat(airCondition.getAirScore(), is(77L));
        assertThat(airCondition.getCategory(), is("Good air quality"));
        assertThat(airCondition.getColor(), is("#65C334"));

    }


}
