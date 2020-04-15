package com.air_quality.backend.cache_statistics;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
@Transactional
public class StatisticsServiceImpl implements StatisticsService {


    @Autowired
    private StatisticsRepository statisticsRepository;

    @Override
    public Statistics getStatistics() {
        Statistics s = this.statisticsRepository.getStatisticsById(0L);
        if (s == null)
            s = this.statisticsRepository.save(new Statistics(0L, 0, 0, 0));
        return s;
    }

    @Override
    public void incrementRequests() {
        Statistics s = this.getStatistics();
        s.setRequestCount(s.getRequestCount() + 1);
        this.statisticsRepository.deleteById(0L);
        this.statisticsRepository.save(s);
    }

    @Override
    public void incrementHits() {
        Statistics s = this.getStatistics();
        s.setHits(s.getHits() + 1);
        this.statisticsRepository.deleteById(0L);
        this.statisticsRepository.save(s);
    }

    @Override
    public void incrementMisses() {
        Statistics s = this.getStatistics();
        s.setMisses(s.getMisses() + 1);
        this.statisticsRepository.deleteById(0L);
        this.statisticsRepository.save(s);
    }


}
