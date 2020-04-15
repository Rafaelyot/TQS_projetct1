package com.air_quality.backend.utils.breezometer_serializers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



@JsonIgnoreProperties(ignoreUnknown = true)
public class BMIndexes {
    private BMBaqi baqi;

    public BMBaqi getBaqi() { return baqi; }
    public void setBaqi(BMBaqi value) { this.baqi = value; }

}
