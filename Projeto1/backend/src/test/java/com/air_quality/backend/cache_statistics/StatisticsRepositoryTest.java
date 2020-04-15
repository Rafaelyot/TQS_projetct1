package com.air_quality.backend.cache_statistics;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.dao.EmptyResultDataAccessException;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DataJpaTest
class StatisticsRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private StatisticsRepository statisticsRepository;

    @Test
    void whenGetStatisticsById_thenReturnStatistics() {
        long id = 1;
        Statistics statistics = new Statistics(id, 0, 0, 0);
        this.entityManager.persistAndFlush(statistics);

        assertThat(this.statisticsRepository.getStatisticsById(id)).isEqualTo(statistics);

    }

    @Test
    void whenGetStatisticsByIdInvalidId_thenReturnStatistics() {
        long id = 1;
        Statistics statistics = new Statistics(id, 0, 0, 0);
        this.entityManager.persistAndFlush(statistics);

        assertThat(this.statisticsRepository.getStatisticsById(0)).isNull();

    }

    @Test
    void whenDeleteStatisticByID_thenReturnDeletion() {
        long id = 1;
        Statistics statistics = new Statistics(id, 0, 0, 0);
        this.entityManager.persistAndFlush(statistics);

        this.statisticsRepository.deleteById(1L);
        assertThat(this.entityManager.find(Statistics.class,1L)).isNull();
    }

    @Test
    void whenDeleteStatisticByIDInvalidID_thenReturnDeletion() {
        long id = 1;
        Statistics statistics = new Statistics(id, 0, 0, 0);
        this.entityManager.persistAndFlush(statistics);


        assertThatThrownBy(()->{
            this.statisticsRepository.deleteById(0L);
        }).isInstanceOf(EmptyResultDataAccessException.class);

    }

    @Test
    void whenSaveAll_thenReturnSavedData() {
        long id = 1;
        Statistics statistics = this.statisticsRepository.save(new Statistics(id, 0, 0, 0));
        Statistics result = this.statisticsRepository.getStatisticsById(1L);

        assertThat(result).isEqualTo(statistics);
        assertThat(result).isEqualTo(this.entityManager.find(Statistics.class,id));
    }


}