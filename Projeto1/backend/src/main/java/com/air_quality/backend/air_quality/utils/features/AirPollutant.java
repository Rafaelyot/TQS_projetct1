package com.air_quality.backend.air_quality.utils.features;


public class AirPollutant {

    private String name;
    private double concentration;
    private String units;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getConcentration() {
        return concentration;
    }

    public void setConcentration(double concentration) {
        this.concentration = concentration;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public AirPollutant(String name, double concentration, String units) {
        this.name = name;
        this.concentration = concentration;
        this.units = units;
    }

    public AirPollutant() {

    }

}
