package com.air_quality.backend.utils;

import com.air_quality.backend.air_quality.utils.features.AirCondition;
import com.air_quality.backend.air_quality.utils.features.AirPollutant;
import com.air_quality.backend.utils.breezometer_serializers.BMHealthRecommendations;
import com.air_quality.backend.utils.breezometer_serializers.BMPollutants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;



class AirConditionComparatorTest {

    private AirConditionComparator airConditionComparator;
    private AirCondition t0;
    private AirCondition t1;

    @BeforeEach
    void setUp() {
        airConditionComparator = new AirConditionComparator();
        t0 = new AirCondition(new Date(), 0L, "category", "color", new AirPollutant("name", 0.0, "units"), new BMPollutants(), new BMHealthRecommendations());
        t1 = new AirCondition(new Date(), 0L, "category", "color", new AirPollutant("name", 0.0, "units"), new BMPollutants(), new BMHealthRecommendations());

    }

    @Test
    void testCompareEarlyDate() {
        // Setup
        this.t0.setDate(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
        this.t1.setDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());

        // Run the test
        final int result = airConditionComparator.compare(t0, t1);

        // Verify the results
        assertThat(result,is(-1));

    }

    @Test
    void testCompareLateDate() {
        // Setup
        this.t0.setDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        this.t1.setDate(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());


        // Run the test
        final int result = airConditionComparator.compare(t0, t1);

        // Verify the results
        assertThat(result,is(1));

    }

    @Test
    void testCompareSameDate() {
        // Setup
        this.t0.setDate(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
        this.t1.setDate(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());

        // Run the test
        final int result = airConditionComparator.compare(t0, t1);

        // Verify the results
        assertThat(result,is(0));

    }
}
