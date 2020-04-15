package com.air_quality.backend.utils.breezometer_serializers;

import com.air_quality.backend.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

class BMBaqiTest {

    private BMBaqi bmAqi;

    @BeforeEach
    void setUp() {
        bmAqi = new BMBaqi();
    }

    @Test
    void getDisplayName() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        String displayName = "displayName";
        assertThat(TestUtils.get(bmAqi, displayName, "displayName", "getDisplayName"), is(displayName));
    }

    @Test
    void setDisplayName() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        String displayName = "displayName";
        assertThat(TestUtils.set(bmAqi, displayName, String.class, "displayName", "setDisplayName"), is(displayName));
    }

    @Test
    void getAqi() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        Long aqi = 1L;
        assertThat(TestUtils.get(bmAqi, aqi, "aqi", "getAqi"), is(aqi));
    }

    @Test
    void setAqi() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        Long aqi = 1L;
        assertThat(TestUtils.set(bmAqi, aqi, Long.class, "aqi", "setAqi"), is(aqi));
    }

    @Test
    void getAqiDisplay() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        String aqiDisplay = "aqiDisplay";
        assertThat(TestUtils.get(bmAqi, aqiDisplay, "aqiDisplay", "getAqiDisplay"), is(aqiDisplay));
    }

    @Test
    void setAqiDisplay() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        String aqiDisplay = "aqiDisplay";
        assertThat(TestUtils.set(bmAqi, aqiDisplay, String.class, "aqiDisplay", "setAqiDisplay"), is(aqiDisplay));
    }

    @Test
    void getColor() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        String color = "color";
        assertThat(TestUtils.get(bmAqi, color, "color", "getColor"), is(color));
    }

    @Test
    void setColor() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        String color = "color";
        assertThat(TestUtils.set(bmAqi, color, String.class, "color", "setColor"), is(color));
    }

    @Test
    void getCategory() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        String category = "category";
        assertThat(TestUtils.get(bmAqi, category, "category", "getCategory"), is(category));
    }

    @Test
    void setCategory() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        String category = "category";
        assertThat(TestUtils.set(bmAqi, category, String.class, "category", "setCategory"), is(category));
    }

    @Test
    void getDominantPollutant() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        String dominantPollutant = "dominantPollutant";
        assertThat(TestUtils.get(bmAqi, dominantPollutant, "dominantPollutant", "getDominantPollutant"), is(dominantPollutant));
    }

    @Test
    void setDominantPollutant() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        String dominantPollutant = "dominantPollutant";
        assertThat(TestUtils.set(bmAqi, dominantPollutant, String.class, "dominantPollutant", "setDominantPollutant"), is(dominantPollutant));
    }
}