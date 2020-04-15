package com.air_quality.backend.cache_statistics;

import com.air_quality.backend.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

class StatisticsTest {

    private Statistics statistics;

    @BeforeEach
    void setUp() {
        this.statistics = new Statistics();
    }

    @Test
    void getId() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        long id = 1l;
        assertThat(TestUtils.get(this.statistics, id, "id", "getId"), is(id));
    }

    @Test
    void setId() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        long id = 1l;
        assertThat(TestUtils.set(this.statistics, id, long.class, "id", "setId"), is(id));
    }

    @Test
    void getRequestCount() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        long requestCount = 1l;
        assertThat(TestUtils.get(this.statistics, requestCount, "requestCount", "getRequestCount"), is(requestCount));
    }

    @Test
    void setRequestCount() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        long requestCount = 1l;
        assertThat(TestUtils.set(this.statistics, requestCount, long.class, "requestCount", "setRequestCount"), is(requestCount));
    }

    @Test
    void getHits() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        long hits = 1l;
        assertThat(TestUtils.get(this.statistics, hits, "hits", "getHits"), is(hits));
    }

    @Test
    void setHits() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        long hits = 1l;
        assertThat(TestUtils.set(this.statistics, hits, long.class, "hits", "setHits"), is(hits));
    }

    @Test
    void getMisses() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        long misses = 1l;
        assertThat(TestUtils.get(this.statistics, misses, "misses", "getMisses"), is(misses));
    }

    @Test
    void setMisses() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        long misses = 1l;
        assertThat(TestUtils.set(this.statistics, misses, long.class, "misses", "setMisses"), is(misses));
    }
}