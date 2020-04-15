package com.air_quality.backend.auto_complete;


import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;


@Entity
public class City {

    public static final int MAZ_SIZE = 1000;
    public static final int TTL = 10 * 60;


    @Id
    @Column(nullable = false)
    private long id;


    @Size(min = 3, max = MAZ_SIZE)
    @Column(nullable = false)
    private String name;

    @Size(min = 3, max = MAZ_SIZE)
    @Column(nullable = false)
    @JsonProperty("full_name")
    private String fullName;


    @Basic(optional = false)
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;

    @Column(nullable = false)
    private double latitude;

    @Column(nullable = false)
    private double longitude;

    public City() {
    }

    public City(long id, String name, String fullName, double latitude, double longitude) {
        this.id = id;
        this.name = name;
        this.fullName = fullName;
        this.latitude = latitude;
        this.longitude = longitude;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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

}
