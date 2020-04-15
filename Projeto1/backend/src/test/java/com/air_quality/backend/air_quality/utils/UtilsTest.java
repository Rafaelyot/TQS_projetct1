package com.air_quality.backend.air_quality.utils;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;


import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;


class UtilsTest {


    @Test
    void getTypeHistorical() {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR, -2);

        assertThat(Utils.getType(calendar.getTime().getTime()), is("historical"));

    }

    @Test
    void getTypeForecast() {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR, 2);

        assertThat(Utils.getType(calendar.getTime().getTime()), is("forecast"));

    }

}