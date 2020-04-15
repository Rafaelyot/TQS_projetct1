package com.air_quality.backend.auto_complete;

import com.air_quality.backend.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

class CityTest {

    private City city;

    @BeforeEach
    void setUp() {
        this.city = new City();
    }

    @Test
    void getTimestamp() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        Date timestamp = new GregorianCalendar(2020, Calendar.APRIL, 6, 1, 0).getTime();
        assertThat(TestUtils.get(this.city, timestamp, "timestamp", "getTimestamp"), is(timestamp));
    }

    @Test
    void getId() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        long id = 1L;
        assertThat(TestUtils.get(this.city, id, "id", "getId"), is(id));
    }

    @Test
    void setId() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        long id = 1L;
        assertThat(TestUtils.set(this.city, id, long.class, "id", "setId"), is(id));
    }

    @Test
    void getName() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        String name = "name";
        assertThat(TestUtils.get(this.city, name, "name", "getName"), is(name));
    }

    @Test
    void setName() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        String name = "name";
        assertThat(TestUtils.set(this.city, name, String.class, "name", "setName"), is(name));
    }

    @Test
    void getFullName() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        String fullName = "full_name";
        assertThat(TestUtils.get(this.city, fullName, "fullName", "getFullName"), is(fullName));
    }

    @Test
    void setFullName() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        String fullName = "full_name";
        assertThat(TestUtils.set(this.city, fullName, String.class, "fullName", "setFullName"), is(fullName));
    }

    @Test
    void getLatitude() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        double latitude = 1.0;
        assertThat(TestUtils.get(this.city, latitude, "latitude", "getLatitude"), is(latitude));
    }

    @Test
    void setLatitude() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        double latitude = 1.0;
        assertThat(TestUtils.set(this.city, latitude, double.class, "latitude", "setLatitude"), is(latitude));
    }

    @Test
    void getLongitude() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        double longitude = 1.0;
        assertThat(TestUtils.get(this.city, longitude, "longitude", "getLongitude"), is(longitude));
    }

    @Test
    void setLongitude() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        double longitude = 1.0;
        assertThat(TestUtils.set(this.city, longitude, double.class, "longitude", "setLongitude"), is(longitude));
    }
}