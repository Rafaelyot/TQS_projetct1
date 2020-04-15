package com.air_quality.backend.air_quality.utils.features;

import com.air_quality.backend.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

class AirPollutantTest {

    private AirPollutant airPollutant;

    @BeforeEach
    void setUp() {
        this.airPollutant = new AirPollutant();
    }

    @Test
    void getName() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        String name = "name";
        assertThat(TestUtils.get(this.airPollutant, name, "name", "getName"), is(name));
    }

    @Test
    void setName() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        String name = "name";
        assertThat(TestUtils.set(this.airPollutant, name, String.class, "name", "setName"), is(name));
    }

    @Test
    void getConcentration() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        double concentration = 1.0;
        assertThat(TestUtils.get(this.airPollutant, concentration, "concentration", "getConcentration"), is(concentration));
    }

    @Test
    void setConcentration() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        double concentration = 1.0;
        assertThat(TestUtils.set(this.airPollutant, concentration, double.class, "concentration", "setConcentration"), is(concentration));
    }

    @Test
    void getUnits() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        String units = "units";
        assertThat(TestUtils.get(this.airPollutant, units, "units", "getUnits"), is(units));
    }

    @Test
    void setUnits() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        String units = "units";
        assertThat(TestUtils.set(this.airPollutant, units, String.class, "units", "setUnits"), is(units));
    }
}