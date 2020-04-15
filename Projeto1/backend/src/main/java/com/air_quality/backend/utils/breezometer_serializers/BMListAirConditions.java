package com.air_quality.backend.utils.breezometer_serializers;

import java.util.*;

public class BMListAirConditions {
    private String metadata;
    private List<BMData> data;
    private String error;

    public String getMetadata() { return metadata; }
    public void setMetadata(String value) { this.metadata = value; }

    public List<BMData> getData() { return data; }
    public void setData(List<BMData> value) { this.data = value; }

    public String getError() { return error; }
    public void setError(String value) { this.error = value; }
}