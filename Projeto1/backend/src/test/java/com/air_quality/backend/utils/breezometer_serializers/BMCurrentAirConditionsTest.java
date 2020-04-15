package com.air_quality.backend.utils.breezometer_serializers;

import com.air_quality.backend.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

class BMCurrentAirConditionsTest {

    private BMCurrentAirConditions bmCurrentAirConditions;

    @BeforeEach
    void setUp() {
        this.bmCurrentAirConditions = new BMCurrentAirConditions();
    }


    @Test
    void getData() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        BMData data = new BMData();
        assertThat(TestUtils.get(this.bmCurrentAirConditions, data, "data", "getData"), is(data));
    }

    @Test
    void setData() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        BMData data = new BMData();
        assertThat(TestUtils.set(this.bmCurrentAirConditions, data, BMData.class, "data", "setData"), is(data));
    }

}