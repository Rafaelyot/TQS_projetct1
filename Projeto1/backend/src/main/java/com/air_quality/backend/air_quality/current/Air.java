package com.air_quality.backend.air_quality.current;

import com.air_quality.backend.air_quality.utils.converters.AirConditionConverter;
import com.air_quality.backend.air_quality.utils.features.AirCondition;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Date;


@Entity
public class Air {

    public static final int MAX_SIZE = 10000000;
    public static final int TTL = 10 * 60;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private double latitude;

    @Column(nullable = false)
    private double longitude;


    @Basic(optional = false)
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;

    @Convert(converter = AirConditionConverter.class)
    @JsonProperty("current_conditions")
    @Column(length = MAX_SIZE)
    private AirCondition currentAirCondition;

    @Basic(optional = false)
    @Column(updatable = false)
    private Date date;


    public Air(double latitude, double longitude, Date date) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.date = date;
    }

    public Air() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getTimestamp() {
        return timestamp;
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


    public AirCondition getCurrentAirCondition() {
        return currentAirCondition;
    }

    public void setCurrentAirCondition(AirCondition currentAirCondition) {
        this.currentAirCondition = currentAirCondition;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
