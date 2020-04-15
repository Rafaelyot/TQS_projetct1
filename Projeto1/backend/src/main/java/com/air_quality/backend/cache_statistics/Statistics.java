package com.air_quality.backend.cache_statistics;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Statistics {

    @Id
    @Column(nullable = false)
    private long id;

    @Column(nullable = false)
    private long requestCount;

    @Column(nullable = false)
    private long hits;

    @Column(nullable = false)
    private long misses;


    public Statistics() {
    }

    public Statistics(long id, long requestCount, long hits, long misses) {
        this.id = id;
        this.requestCount = requestCount;
        this.hits = hits;
        this.misses = misses;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getRequestCount() {
        return requestCount;
    }

    public void setRequestCount(long requestCount) {
        this.requestCount = requestCount;
    }

    public long getHits() {
        return hits;
    }

    public void setHits(long hits) {
        this.hits = hits;
    }

    public long getMisses() {
        return misses;
    }

    public void setMisses(long misses) {
        this.misses = misses;
    }
}
