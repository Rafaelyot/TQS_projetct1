package com.air_quality.backend.utils.cities_search_serializers;

import com.air_quality.backend.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

class EmbeddedTest {

    private Embedded embedded;

    @BeforeEach
    void setUp() {
        this.embedded = new Embedded();
    }

    @Test
    void getCitySearchResults() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        List<CitySearchResult> citySearchResults = new ArrayList<>();
        citySearchResults.add(new CitySearchResult());
        assertThat(TestUtils.get(this.embedded, citySearchResults, "citySearchResults", "getCitySearchResults"), is(citySearchResults));
    }
}