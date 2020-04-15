package com.air_quality.backend.air_quality.temporal;

import com.air_quality.backend.TestUtils;
import com.air_quality.backend.air_quality.utils.features.AirCondition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.Is.is;

class TemporalAirTest {

    private TemporalAir temporalAir;

    @BeforeEach
    void setUp() {
        this.temporalAir = new TemporalAir();
    }

    @Test
    void testConstructor() {
        double latitude = 2.0;
        double longitude = 2.0;
        int hours = 2;
        TemporalAir test = new TemporalAir(latitude, longitude, hours);

        assertThat(test,is(notNullValue()));
        assertThat(test.getLatitude(),is(latitude));
        assertThat(test.getLongitude(),is(longitude));
        assertThat(test.getNumberHours(),is(hours));
    }

    @Test
    void getTimestamp() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        Date timestamp = new GregorianCalendar(2020, Calendar.APRIL, 6, 1, 0).getTime();
        assertThat(TestUtils.get(this.temporalAir, timestamp, "timestamp", "getTimestamp"), is(timestamp));
    }

    @Test
    void getId() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        long id = 1L;
        assertThat(TestUtils.get(this.temporalAir, id, "id", "getId"), is(id));
    }

    @Test
    void setId() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        long id = 1L;
        assertThat(TestUtils.set(this.temporalAir, id, long.class, "id", "setId"), is(id));
    }

    @Test
    void getLatitude() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        double latitude = 1.0;
        assertThat(TestUtils.get(this.temporalAir, latitude, "latitude", "getLatitude"), is(latitude));
    }

    @Test
    void setLatitude() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        double latitude = 1.0;
        assertThat(TestUtils.set(this.temporalAir, latitude, double.class, "latitude", "setLatitude"), is(latitude));
    }

    @Test
    void getLongitude() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        double longitude = 1.0;
        assertThat(TestUtils.get(this.temporalAir, longitude, "longitude", "getLongitude"), is(longitude));
    }

    @Test
    void setLongitude() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        double longitude = 1.0;
        assertThat(TestUtils.set(this.temporalAir, longitude, double.class, "longitude", "setLongitude"), is(longitude));
    }

    @Test
    void getTemporalAirConditions() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        List<AirCondition> temporalAirConditions = new ArrayList<>();
        temporalAirConditions.add(new AirCondition());
        assertThat(TestUtils.get(this.temporalAir, temporalAirConditions, "temporalAirConditions", "getTemporalAirConditions"), is(temporalAirConditions));

    }

    @Test
    void setTemporalAirConditions() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        List<AirCondition> temporalAirConditions = new ArrayList<>();
        temporalAirConditions.add(new AirCondition());
        assertThat(TestUtils.set(this.temporalAir, temporalAirConditions, List.class, "temporalAirConditions", "setTemporalAirConditions"), is(temporalAirConditions));
    }

    @Test
    void getNumberHours() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        int numberHours = 1;
        assertThat(TestUtils.get(this.temporalAir, numberHours, "numberHours", "getNumberHours"), is(numberHours));
    }

    @Test
    void setNumberHours() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        int numberHours = 1;
        assertThat(TestUtils.set(this.temporalAir, numberHours, int.class, "numberHours", "setNumberHours"), is(numberHours));
    }
}