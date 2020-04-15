package com.air_quality.backend.utils.breezometer_serializers;

import com.air_quality.backend.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

class BMDataTest {

    private BMData bmData;

    @BeforeEach
    void setUp() {
        this.bmData = new BMData();
    }

    @Test
    void getDatetime() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        String datetime = "datetime";
        assertThat(TestUtils.get(this.bmData, datetime, "datetime", "getDatetime"), is(datetime));

    }

    @Test
    void setDatetime() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        String datetime = "datetime";
        assertThat(TestUtils.set(this.bmData, datetime, String.class, "datetime", "setDatetime"), is(datetime));
    }

    @Test
    void getIndexes() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        BMIndexes indexes = new BMIndexes();
        assertThat(TestUtils.get(this.bmData, indexes, "indexes", "getIndexes"), is(indexes));
    }

    @Test
    void setIndexes() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        BMIndexes indexes = new BMIndexes();
        assertThat(TestUtils.set(this.bmData, indexes, BMIndexes.class, "indexes", "setIndexes"), is(indexes));
    }

    @Test
    void getPollutants() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        BMPollutants pollutants = new BMPollutants();
        assertThat(TestUtils.get(this.bmData, pollutants, "pollutants", "getPollutants"), is(pollutants));
    }

    @Test
    void setPollutants() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        BMPollutants pollutants = new BMPollutants();
        assertThat(TestUtils.set(this.bmData, pollutants, BMPollutants.class, "pollutants", "setPollutants"), is(pollutants));
    }

    @Test
    void getHealthRecommendations() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        BMHealthRecommendations healthRecommendations = new BMHealthRecommendations();
        assertThat(TestUtils.get(this.bmData, healthRecommendations, "healthRecommendations", "getHealthRecommendations"), is(healthRecommendations));
    }

    @Test
    void setHealthRecommendations() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        BMHealthRecommendations healthRecommendations = new BMHealthRecommendations();
        assertThat(TestUtils.set(this.bmData, healthRecommendations, BMHealthRecommendations.class, "healthRecommendations", "setHealthRecommendations"), is(healthRecommendations));
    }
}