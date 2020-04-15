package com.air_quality.backend.utils.breezometer_serializers;

import com.air_quality.backend.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

class BMIndexesTest {

    private BMIndexes bmIndexes;

    @BeforeEach
    void setUp() {
        bmIndexes = new BMIndexes();
    }

    @Test
    void getBaqi() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        BMBaqi baqi = new BMBaqi();
        assertThat(TestUtils.get(this.bmIndexes, baqi, "baqi", "getBaqi"), is(baqi));
    }

    @Test
    void setBaqi() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        BMBaqi baqi = new BMBaqi();
        assertThat(TestUtils.set(this.bmIndexes, baqi, BMBaqi.class, "baqi", "setBaqi"), is(baqi));

    }

}