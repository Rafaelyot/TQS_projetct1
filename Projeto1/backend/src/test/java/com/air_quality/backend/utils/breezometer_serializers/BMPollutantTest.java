package com.air_quality.backend.utils.breezometer_serializers;

import com.air_quality.backend.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

class BMPollutantTest {

    private BMPollutant bmPollutant;

    @BeforeEach
    void setUp() {
        this.bmPollutant = new BMPollutant();
    }

    @Test
    void getDisplayName() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        String displayName = "displayName";
        assertThat(TestUtils.get(this.bmPollutant, displayName, "displayName", "getDisplayName"), is(displayName));
    }

    @Test
    void setDisplayName() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        String displayName = "displayName";
        assertThat(TestUtils.set(this.bmPollutant, displayName, String.class, "displayName", "setDisplayName"), is(displayName));
    }

    @Test
    void getFullName() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        String fullName = "fullName";
        assertThat(TestUtils.get(this.bmPollutant, fullName, "fullName", "getFullName"), is(fullName));
    }

    @Test
    void setFullName() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        String fullName = "fullName";
        assertThat(TestUtils.set(this.bmPollutant, fullName, String.class, "fullName", "setFullName"), is(fullName));
    }

    @Test
    void getAqiInformation() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        BMAqiInformation aqiInformation = new BMAqiInformation();
        assertThat(TestUtils.get(this.bmPollutant, aqiInformation, "aqiInformation", "getAqiInformation"), is(aqiInformation));
    }

    @Test
    void setAqiInformation() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        BMAqiInformation aqiInformation = new BMAqiInformation();
        assertThat(TestUtils.set(this.bmPollutant, aqiInformation, BMAqiInformation.class, "aqiInformation", "setAqiInformation"), is(aqiInformation));
    }

    @Test
    void getConcentration() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        BMConcentration concentration = new BMConcentration();
        assertThat(TestUtils.get(this.bmPollutant, concentration, "concentration", "getConcentration"), is(concentration));
    }

    @Test
    void setConcentration() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        BMConcentration concentration = new BMConcentration();
        assertThat(TestUtils.set(this.bmPollutant, concentration, BMConcentration.class, "concentration", "setConcentration"), is(concentration));
    }

    @Test
    void getSourcesAndEffects() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        BMSourcesAndEffects sourcesAndEffects = new BMSourcesAndEffects();
        assertThat(TestUtils.get(this.bmPollutant, sourcesAndEffects, "sourcesAndEffects", "getSourcesAndEffects"), is(sourcesAndEffects));
    }

    @Test
    void setSourcesAndEffects() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        BMSourcesAndEffects sourcesAndEffects = new BMSourcesAndEffects();
        assertThat(TestUtils.set(this.bmPollutant, sourcesAndEffects, BMSourcesAndEffects.class, "sourcesAndEffects", "setSourcesAndEffects"), is(sourcesAndEffects));

    }
}