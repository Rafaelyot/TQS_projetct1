package com.air_quality.backend.air_quality.temporal;

import com.air_quality.backend.air_quality.utils.converters.ListAirConditionConverter;
import com.air_quality.backend.air_quality.utils.features.AirCondition;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Entity
public class TemporalAir implements Cloneable {

    public static final int TTL = 10 * 60;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private double latitude;

    @Column(nullable = false)
    private double longitude;

    public static final int MAX_SIZE = Integer.MAX_VALUE;

    @Basic(optional = false)
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;

    @Column(nullable = false)
    @JsonProperty("number_hours")
    private int numberHours;

    @Convert(converter = ListAirConditionConverter.class)
    @JsonProperty("temporal_conditions")
    @Column(length = MAX_SIZE)
    private List<AirCondition> temporalAirConditions;


    public TemporalAir(double latitude, double longitude, int numberHours) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.numberHours = numberHours;
    }

    public TemporalAir() {

    }

    public Date getTimestamp() {
        return timestamp;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public List<AirCondition> getTemporalAirConditions() {
        return temporalAirConditions;
    }

    public void setTemporalAirConditions(List<AirCondition> temporalAirConditions) {
        this.temporalAirConditions = temporalAirConditions;
    }

    public int getNumberHours() {
        return numberHours;
    }

    public void setNumberHours(int numberHours) {
        this.numberHours = numberHours;
    }


    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

}
