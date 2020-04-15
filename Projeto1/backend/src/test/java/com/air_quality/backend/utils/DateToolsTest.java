package com.air_quality.backend.utils;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.Calendar;

import java.util.GregorianCalendar;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DateToolsTest {

    @SuppressWarnings("ResultOfMethodCallIgnored")


    private String strDate = "2020-04-06T00:00:00Z";
    private GregorianCalendar date = new GregorianCalendar(2020, Calendar.APRIL, 6, 0, 0); // Due to the timezone

    @Test
    void testParse() throws Exception {
        assertThat(DateTools.parse(strDate), is(date.getTime()));
        assertThrows(ParseException.class, () -> {
            DateTools.parse("2015-09-09");
        });
    }

    @Test
    void convertSecondsToDate() {
        long seconds = 1586131200000L;
        assertThat(DateTools.convertSecondsToDate(seconds),is(date.getTime()));
    }


    @Test
    void convertSecondsToBMDate() {
        long seconds = 1586818800000L;
        String bmDate = "2020-04-13T23:00:00";
        assertThat(DateTools.convertSecondsToBMDate(seconds),is(bmDate));
    }

    @Test
    void testToString() {
        String dateStr = "2020-04-06T00:00+00:00";
        assertThat(DateTools.toString(date.getTime()),is(dateStr));
    }



}




