package com.air_quality.backend.utils.breezometer_serializers;

import com.air_quality.backend.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

class BMConcentrationTest {

    private BMConcentration bmConcentration;

    @BeforeEach
    void setUp() {
        this.bmConcentration = new BMConcentration();
    }


    /*
        private double value;
    private String units;
     */
    @Test
    void getValue() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        double value = 2.0;
        assertThat(TestUtils.get(this.bmConcentration, value, "value", "getValue"), is(value));
    }

    @Test
    void setValue() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        double value = 2.0;
        assertThat(TestUtils.set(this.bmConcentration, value, double.class, "value", "setValue"), is(value));
    }

    @Test
    void getUnits() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        String units = "units";
        assertThat(TestUtils.get(this.bmConcentration, units, "units", "getUnits"), is(units));
    }

    @Test
    void setUnits() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        String units = "units";
        assertThat(TestUtils.set(this.bmConcentration, units, String.class, "units", "setUnits"), is(units));
    }
}