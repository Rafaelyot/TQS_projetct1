package com.air_quality.backend.air_quality.utils.features;

import com.air_quality.backend.TestUtils;
import com.air_quality.backend.utils.breezometer_serializers.BMHealthRecommendations;
import com.air_quality.backend.utils.breezometer_serializers.BMPollutants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

class AirConditionTest {
    private AirCondition airCondition;

    @BeforeEach
    void setUp() {
        this.airCondition = new AirCondition();
    }

    @Test
    void getDate() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        Date date = new Date();
        assertThat(TestUtils.get(this.airCondition, date, "date", "getDate"), is(date));
    }

    @Test
    void setDate() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        Date date = new Date();
        assertThat(TestUtils.set(this.airCondition, date, Date.class, "date", "setDate"), is(date));
    }

    @Test
    void getAirScore() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        long airScore = 0L;
        assertThat(TestUtils.get(this.airCondition, airScore, "airScore", "getAirScore"), is(airScore));
    }

    @Test
    void setAirScore() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        long airScore = 0L;
        assertThat(TestUtils.set(this.airCondition, airScore, long.class, "airScore", "setAirScore"), is(airScore));
    }

    @Test
    void getCategory() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        String category = "category";
        assertThat(TestUtils.get(this.airCondition, category, "category", "getCategory"), is(category));
    }

    @Test
    void setCategory() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        String category = "category";
        assertThat(TestUtils.set(this.airCondition, category, String.class, "category", "setCategory"), is(category));
    }

    @Test
    void getColor() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        String color = "color";
        assertThat(TestUtils.get(this.airCondition, color, "color", "getColor"), is(color));
    }

    @Test
    void setColor() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        String color = "color";
        assertThat(TestUtils.set(this.airCondition, color, String.class, "color", "setColor"), is(color));
    }

    @Test
    void getDominantPollutant() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        AirPollutant dominantPollutant = new AirPollutant();
        assertThat(TestUtils.get(this.airCondition, dominantPollutant, "dominantPollutant", "getDominantPollutant"), is(dominantPollutant));
    }

    @Test
    void setDominantPollutant() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        AirPollutant dominantPollutant = new AirPollutant();
        assertThat(TestUtils.set(this.airCondition, dominantPollutant, AirPollutant.class, "dominantPollutant", "setDominantPollutant"), is(dominantPollutant));

    }

    @Test
    void getPollutants() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        BMPollutants pollutants = new BMPollutants();
        assertThat(TestUtils.get(this.airCondition, pollutants, "pollutants", "getPollutants"), is(pollutants));

    }

    @Test
    void setPollutants() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        BMPollutants pollutants = new BMPollutants();
        assertThat(TestUtils.set(this.airCondition, pollutants, BMPollutants.class, "pollutants", "setPollutants"), is(pollutants));
    }

    @Test
    void getHealthRecommendations() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        BMHealthRecommendations healthRecommendations = new BMHealthRecommendations();
        assertThat(TestUtils.get(this.airCondition, healthRecommendations, "healthRecommendations", "getHealthRecommendations"), is(healthRecommendations));
    }

    @Test
    void setHealthRecommendations() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        BMHealthRecommendations healthRecommendations = new BMHealthRecommendations();
        assertThat(TestUtils.set(this.airCondition, healthRecommendations, BMHealthRecommendations.class, "healthRecommendations", "setHealthRecommendations"), is(healthRecommendations));

    }
}