package com.air_quality.backend.utils.breezometer_serializers;

public class BMConcentration {
    private double value;
    private String units;

    public double getValue() { return value; }
    public void setValue(double value) { this.value = value; }

    public String getUnits() { return units; }
    public void setUnits(String value) { this.units = value; }
}
