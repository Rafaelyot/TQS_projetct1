package com.air_quality.backend.utils.breezometer_serializers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BMCurrentAirConditions {

    private BMData data;


    public BMData getData() { return data; }
    public void setData(BMData value) { this.data = value; }

}
