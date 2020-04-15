package com.air_quality.backend.utils.breezometer_serializers;

import com.air_quality.backend.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

class BMAqiInformationTest {

    private BMAqiInformation bmAqiInformation;

    @BeforeEach
    void setUp() {
        bmAqiInformation = new BMAqiInformation();
    }


    @Test
    void getBaqi() throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        BMBaqi baqi = new BMBaqi();
        assertThat(TestUtils.get(bmAqiInformation, baqi, "baqi", "getBaqi"), is(baqi));
    }

    @Test
    void setBaqi() throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        BMBaqi baqi = new BMBaqi();
        assertThat(TestUtils.set(bmAqiInformation, baqi, BMBaqi.class, "baqi", "setBaqi"), is(baqi));
    }
}
