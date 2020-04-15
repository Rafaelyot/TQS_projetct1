package com.air_quality.backend.utils.breezometer_serializers;

import com.air_quality.backend.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

class BMSourcesAndEffectsTest {

    private BMSourcesAndEffects bmSourcesAndEffects;

    @BeforeEach
    void setUp() {
        this.bmSourcesAndEffects = new BMSourcesAndEffects();
    }

    @Test
    void getSources() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        String sources = "sources";
        assertThat(TestUtils.get(this.bmSourcesAndEffects, sources, "sources", "getSources"), is(sources));
    }

    @Test
    void setSources() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        String sources = "sources";
        assertThat(TestUtils.set(this.bmSourcesAndEffects, sources, String.class, "sources", "setSources"), is(sources));
    }

    @Test
    void getEffects() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        String effects = "effects";
        assertThat(TestUtils.get(this.bmSourcesAndEffects, effects, "effects", "getEffects"), is(effects));
    }

    @Test
    void setEffects() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        String effects = "effects";
        assertThat(TestUtils.set(this.bmSourcesAndEffects, effects, String.class, "effects", "setEffects"), is(effects));
    }
}