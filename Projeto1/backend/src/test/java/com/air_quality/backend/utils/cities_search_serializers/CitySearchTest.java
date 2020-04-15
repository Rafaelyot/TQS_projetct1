package com.air_quality.backend.utils.cities_search_serializers;

import com.air_quality.backend.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

class CitySearchTest {

    private CitySearch citySearch;

    @BeforeEach
    void setUp() {
        this.citySearch = new CitySearch();
    }

    @Test
    void getEmbedded() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        Embedded embedded = new Embedded();
        assertThat(TestUtils.get(this.citySearch, embedded, "embedded", "getEmbedded"), is(embedded));
    }


}