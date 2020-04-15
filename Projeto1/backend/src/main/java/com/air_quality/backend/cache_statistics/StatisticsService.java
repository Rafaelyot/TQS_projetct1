package com.air_quality.backend.cache_statistics;

public interface StatisticsService {
    public Statistics getStatistics();

    public void incrementRequests();

    public void incrementHits();

    public void incrementMisses();


}
