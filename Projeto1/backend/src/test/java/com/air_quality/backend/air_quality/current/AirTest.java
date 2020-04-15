package com.air_quality.backend.air_quality.current;

import com.air_quality.backend.TestUtils;
import com.air_quality.backend.air_quality.utils.features.AirCondition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

class AirTest {

    private Air air;

    @BeforeEach
    void setUp() {
        this.air = new Air();
    }


    @Test
    void testConstructor() {
        double latitude = 2.0;
        double longitude = 2.0;
        Date date = new GregorianCalendar(2020, Calendar.APRIL, 6, 1, 0).getTime();
        Air test = new Air(latitude, longitude, date);


        assertThat(test, is(notNullValue()));
        assertThat(test.getLatitude(), is(latitude));
        assertThat(test.getLongitude(), is(longitude));
        assertThat(test.getDate(), is(date));

    }

    @Test
    void getId() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        long id = 1L;
        assertThat(TestUtils.get(this.air, id, "id", "getId"), is(id));
    }

    @Test
    void setId() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        long id = 1L;
        assertThat(TestUtils.set(this.air, id, long.class, "id", "setId"), is(id));
    }

    @Test
    void getTimestamp() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        Date timestamp = new GregorianCalendar(2020, Calendar.APRIL, 6, 1, 0).getTime();
        assertThat(TestUtils.get(this.air, timestamp, "timestamp", "getTimestamp"), is(timestamp));
    }

    @Test
    void getLatitude() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        double latitude = 1.0;
        assertThat(TestUtils.get(this.air, latitude, "latitude", "getLatitude"), is(latitude));
    }

    @Test
    void setLatitude() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        double latitude = 1.0;
        assertThat(TestUtils.set(this.air, latitude, double.class, "latitude", "setLatitude"), is(latitude));
    }

    @Test
    void getLongitude() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        double longitude = 1.0;
        assertThat(TestUtils.get(this.air, longitude, "longitude", "getLongitude"), is(longitude));
    }

    @Test
    void setLongitude() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        double longitude = 1.0;
        assertThat(TestUtils.set(this.air, longitude, double.class, "longitude", "setLongitude"), is(longitude));
    }

    @Test
    void getCurrentAirCondition() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        AirCondition currentAirCondition = new AirCondition();
        assertThat(TestUtils.get(this.air, currentAirCondition, "currentAirCondition", "getCurrentAirCondition"), is(currentAirCondition));
    }

    @Test
    void setCurrentAirCondition() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        AirCondition currentAirCondition = new AirCondition();
        assertThat(TestUtils.set(this.air, currentAirCondition, AirCondition.class, "currentAirCondition", "setCurrentAirCondition"), is(currentAirCondition));
    }

    @Test
    void getDate() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        Date date = new GregorianCalendar(2020, Calendar.APRIL, 6, 1, 0).getTime();
        assertThat(TestUtils.get(this.air, date, "date", "getDate"), is(date));
    }

    @Test
    void setDate() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        Date date = new GregorianCalendar(2020, Calendar.APRIL, 6, 1, 0).getTime();
        assertThat(TestUtils.set(this.air, date, Date.class, "date", "setDate"), is(date));
    }
}