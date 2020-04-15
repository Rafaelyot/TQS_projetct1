package com.air_quality.backend.air_quality.utils.features;

import com.air_quality.backend.utils.breezometer_serializers.BMHealthRecommendations;
import com.air_quality.backend.utils.breezometer_serializers.BMPollutants;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class AirCondition {

    private Date date;

    @JsonProperty("air_score")
    private long airScore;
    private String category;
    private String color;

    @JsonProperty("dominant_pollutant")
    private AirPollutant dominantPollutant;

    private BMPollutants pollutants;

    @JsonProperty("health_recommendations")
    private BMHealthRecommendations healthRecommendations;


    public AirCondition(Date date, long airScore, String category, String color, AirPollutant airPollutant, BMPollutants pollutants, BMHealthRecommendations healthRecommendations) {
        this.date = date;
        this.airScore = airScore;
        this.category = category;
        this.color = color;
        this.dominantPollutant = airPollutant;
        this.pollutants = pollutants;
        this.healthRecommendations = healthRecommendations;

    }

    public AirCondition() {

    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public long getAirScore() {
        return airScore;
    }

    public void setAirScore(long airScore) {
        this.airScore = airScore;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public AirPollutant getDominantPollutant() {
        return dominantPollutant;
    }

    public void setDominantPollutant(AirPollutant dominantPollutant) {
        this.dominantPollutant = dominantPollutant;
    }

    public BMPollutants getPollutants() {
        return pollutants;
    }

    public void setPollutants(BMPollutants pollutants) {
        this.pollutants = pollutants;
    }

    public BMHealthRecommendations getHealthRecommendations() {
        return healthRecommendations;
    }

    public void setHealthRecommendations(BMHealthRecommendations healthRecommendations) {
        this.healthRecommendations = healthRecommendations;
    }


}
