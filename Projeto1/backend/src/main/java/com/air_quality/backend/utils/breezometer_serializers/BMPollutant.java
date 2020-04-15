package com.air_quality.backend.utils.breezometer_serializers;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BMPollutant {

    @JsonProperty("display_name")
    private String displayName;

    @JsonProperty("full_name")
    private String fullName;

    @JsonProperty("aqi_information")
    private BMAqiInformation aqiInformation;
    private BMConcentration concentration;

    @JsonProperty("sources_and_effects")
    private BMSourcesAndEffects sourcesAndEffects;

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String value) {
        this.displayName = value;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String value) {
        this.fullName = value;
    }

    public BMAqiInformation getAqiInformation() {
        return aqiInformation;
    }

    public void setAqiInformation(BMAqiInformation value) {
        this.aqiInformation = value;
    }

    public BMConcentration getConcentration() {
        return concentration;
    }

    public void setConcentration(BMConcentration value) {
        this.concentration = value;
    }

    public BMSourcesAndEffects getSourcesAndEffects() {
        return sourcesAndEffects;
    }

    public void setSourcesAndEffects(BMSourcesAndEffects value) {
        this.sourcesAndEffects = value;
    }

}
