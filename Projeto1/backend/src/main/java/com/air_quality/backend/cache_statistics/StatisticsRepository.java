package com.air_quality.backend.cache_statistics;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StatisticsRepository extends JpaRepository<Statistics, Long> {
    public Statistics getStatisticsById(long id);


}
