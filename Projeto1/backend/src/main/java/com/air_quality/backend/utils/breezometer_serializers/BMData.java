package com.air_quality.backend.utils.breezometer_serializers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BMData {
    private String datetime;

    private BMIndexes indexes;
    private BMPollutants pollutants;

    @JsonProperty("health_recommendations")
    private BMHealthRecommendations healthRecommendations;

    public String getDatetime() { return datetime; }
    public void setDatetime(String value) { this.datetime = value; }

    public BMIndexes getIndexes() { return indexes; }
    public void setIndexes(BMIndexes value) { this.indexes = value; }

    public BMPollutants getPollutants() { return pollutants; }
    public void setPollutants(BMPollutants value) { this.pollutants = value; }

    public BMHealthRecommendations getHealthRecommendations() { return healthRecommendations; }
    public void setHealthRecommendations(BMHealthRecommendations value) { this.healthRecommendations = value; }
}
