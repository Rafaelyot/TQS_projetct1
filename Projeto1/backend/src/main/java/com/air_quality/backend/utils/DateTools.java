package com.air_quality.backend.utils;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateTools {
    private DateTools() {

    }


    public static Date parse(String input) throws ParseException { // Just accepts JSON String data formats and gmt date types

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssz");

        //this is zero time so we need to add that TZ indicator for
        if (input.endsWith("Z")) {
            input = input.substring(0, input.length() - 1) + "GMT-00:00";
        } else {
            int inset = 6;

            String s0 = input.substring(0, input.length() - inset);
            String s1 = input.substring(input.length() - inset);

            input = s0 + "GMT" + s1;
        }

        return df.parse(input);
    }

    public static Date convertSecondsToDate(long date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(date);
        return calendar.getTime();
    }

    public static String convertSecondsToBMDate(long date) {
        return DateTools.toString(DateTools.convertSecondsToDate(date)).replace("+00:00", ":00"); // because of breezometter
    }

    public static String toString(Date date) {

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssz");

        TimeZone tz = TimeZone.getTimeZone("UTC");

        df.setTimeZone(tz);

        String output = df.format(date);

        int inset0 = 9;
        int inset1 = 6;

        String s0 = output.substring(0, output.length() - inset0);
        String s1 = output.substring(output.length() - inset1, output.length());

        String result = s0 + s1;

        result = result.replace("UTC", "+00:00");

        return result;

    }

    public static Date getCurrentDate() { //ignoring minutes,seconds and mseconds
        Date now = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        Calendar expected = Calendar.getInstance();
        expected.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.HOUR_OF_DAY), 0, 0);
        expected.set(Calendar.MILLISECOND, 0);
        return expected.getTime();
    }

    public static Date getDateMinusMilliSeconds(long seconds) {
        Date now = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        long t = calendar.getTimeInMillis() - seconds * 1000;
        return new Date(t);
    }


}
