package com.air_quality.backend.utils.breezometer_serializers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BMHealthRecommendations {
    @JsonProperty("general_population")
    private String generalPopulation;
    private String elderly;

    @JsonProperty("lung_diseases")
    private String lungDiseases;

    @JsonProperty("heart_diseases")
    private String heartDiseases;
    private String active;

    @JsonProperty("pregnant_women")
    private String pregnantWomen;
    private String children;

    public String getGeneralPopulation() { return generalPopulation; }
    public void setGeneralPopulation(String value) { this.generalPopulation = value; }

    public String getElderly() { return elderly; }
    public void setElderly(String value) { this.elderly = value; }

    public String getLungDiseases() { return lungDiseases; }
    public void setLungDiseases(String value) { this.lungDiseases = value; }

    public String getHeartDiseases() { return heartDiseases; }
    public void setHeartDiseases(String value) { this.heartDiseases = value; }

    public String getActive() { return active; }
    public void setActive(String value) { this.active = value; }

    public String getPregnantWomen() { return pregnantWomen; }
    public void setPregnantWomen(String value) { this.pregnantWomen = value; }

    public String getChildren() { return children; }
    public void setChildren(String value) { this.children = value; }
}
