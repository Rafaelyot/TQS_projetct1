package com.air_quality.backend.utils.breezometer_serializers;

import com.air_quality.backend.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

class BMListAirConditionsTest {

    private BMListAirConditions bmListAirConditions;

    @BeforeEach
    void setUp() {
        this.bmListAirConditions = new BMListAirConditions();
    }

    @Test
    void getMetadata() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        String metadata = "metadata";
        assertThat(TestUtils.get(this.bmListAirConditions, metadata, "metadata", "getMetadata"), is(metadata));
    }

    @Test
    void setMetadata() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        String metadata = "metadata";
        assertThat(TestUtils.set(this.bmListAirConditions, metadata, String.class, "metadata", "setMetadata"), is(metadata));
    }

    @Test
    void getData() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        List<BMData> data = new ArrayList<>();
        data.add(new BMData());
        assertThat(TestUtils.get(this.bmListAirConditions, data, "data", "getData"), is(data));
    }

    @Test
    void setData() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        List<BMData> data = new ArrayList<>();
        data.add(new BMData());
        assertThat(TestUtils.set(this.bmListAirConditions, data, List.class, "data", "setData"), is(data));
    }

    @Test
    void getError() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        String error = "error";
        assertThat(TestUtils.get(this.bmListAirConditions, error, "error", "getError"), is(error));
    }

    @Test
    void setError() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        String error = "error";
        assertThat(TestUtils.set(this.bmListAirConditions, error, String.class, "error", "setError"), is(error));
    }
}