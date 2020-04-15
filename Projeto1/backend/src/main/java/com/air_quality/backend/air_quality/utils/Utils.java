package com.air_quality.backend.air_quality.utils;

import com.air_quality.backend.utils.DateTools;

import java.util.Date;

public class Utils {

    private Utils() {

    }

    public static String getType(long dateSeconds) {
        String type = "historical";
        Date date = DateTools.convertSecondsToDate(dateSeconds);
        Date currentDate = DateTools.convertSecondsToDate(System.currentTimeMillis());

        if (date.compareTo(currentDate) > 0)
            type = "forecast";

        return type;
    }

}
