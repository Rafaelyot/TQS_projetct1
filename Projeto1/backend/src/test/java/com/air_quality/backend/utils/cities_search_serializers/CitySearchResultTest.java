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

class CitySearchResultTest {

    private CitySearchResult citySearchResult;

    @BeforeEach
    void setUp() {
        this.citySearchResult = new CitySearchResult();
    }

    @Test
    void getHref() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        String href = "href";
        assertThat(TestUtils.get(this.citySearchResult, href, "href", "getHref"), is(href));
    }

    @Test
    void getMatchingFullName() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        String matchingFullName = "matchingFullName";
        assertThat(TestUtils.get(this.citySearchResult, matchingFullName, "matchingFullName", "getMatchingFullName"), is(matchingFullName));
    }
}