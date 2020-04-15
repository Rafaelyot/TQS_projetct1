package com.air_quality.backend.utils;

import com.air_quality.backend.air_quality.utils.features.AirCondition;

import java.util.Comparator;

public class AirConditionComparator implements Comparator<AirCondition> {
    @Override
    public int compare(AirCondition t0, AirCondition t1) {
        return t0.getDate().compareTo(t1.getDate());
    }
}
