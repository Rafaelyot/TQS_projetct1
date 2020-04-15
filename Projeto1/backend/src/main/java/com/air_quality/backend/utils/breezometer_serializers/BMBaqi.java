package com.air_quality.backend.utils.breezometer_serializers;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BMBaqi {

    @JsonProperty("display_name")
    private String displayName;
    private Long aqi;

    @JsonProperty("aqi_display")
    private String aqiDisplay;
    private String color;
    private String category;

    @JsonProperty("dominant_pollutant")
    private String dominantPollutant;

    public String getDisplayName() { return displayName; }
    public void setDisplayName(String value) { this.displayName = value; }

    public Long getAqi() { return aqi; }
    public void setAqi(Long value) { this.aqi = value; }

    public String getAqiDisplay() { return aqiDisplay; }
    public void setAqiDisplay(String value) { this.aqiDisplay = value; }

    public String getColor() { return color; }
    public void setColor(String value) { this.color = value; }

    public String getCategory() { return category; }
    public void setCategory(String value) { this.category = value; }

    public String getDominantPollutant() { return dominantPollutant; }
    public void setDominantPollutant(String value) { this.dominantPollutant = value; }
}
