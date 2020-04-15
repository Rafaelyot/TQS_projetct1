package com.air_quality.backend.air_quality.current;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Calendar;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class AirRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AirRepository airRepository;


    @Test
    void whenGetAirByLatitudeAndLongitudeAndDate_thenReturnAir() {
        Date now = new Date();
        double latitude = 2.0;
        double longitude = 3.0;

        Air air = new Air(latitude, longitude, now);
        this.entityManager.persistAndFlush(air);

        Air result = this.airRepository.getAirByLatitudeAndLongitudeAndDate(latitude, longitude, now);
        assertThat(result).isEqualTo(air);
    }

    @Test
    void whenGetAirByLatitudeAndLongitudeAndDateInvalidCoordinates_thenReturnAir() {
        Date now = new Date();
        double latitude = 2.0;
        double longitude = 3.0;
        Air air = new Air(latitude, longitude, now);
        this.entityManager.persistAndFlush(air);

        Air result = this.airRepository.getAirByLatitudeAndLongitudeAndDate(1.0, 1.0, now);
        assertThat(result).isNull();
    }

    @Test
    void whenGetAirByLatitudeAndLongitudeAndDateInvalidDate_thenReturnAir() {
        Date now = new Date();
        double latitude = 2.0;
        double longitude = 3.0;
        Air air = new Air(latitude, longitude, now);
        this.entityManager.persistAndFlush(air);

        Air result = this.airRepository.getAirByLatitudeAndLongitudeAndDate(latitude, longitude, new Date());
        assertThat(result).isNull();
    }

    @Test
    void whenDeleteAirByTimestampBefore_thenReturnDeletion() {
        Date now = new Date();
        double latitude = 2.0;
        double longitude = 3.0;

        Air air = new Air(latitude, longitude, now);

        this.entityManager.persistAndFlush(air);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        calendar.add(Calendar.SECOND, Air.TTL);

        assertThat(this.airRepository.deleteAirByTimestampBefore(calendar.getTime())).isEqualTo(1);

    }

    @Test
    void whenDeleteAirByTimestampBefore_thenReturnNonDeletion() {
        Date now = new Date();
        double latitude = 2.0;
        double longitude = 3.0;

        Air air = new Air(latitude, longitude, now);
        this.entityManager.persistAndFlush(air);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        calendar.add(Calendar.SECOND, -(Air.TTL + 60));

        assertThat(this.airRepository.deleteAirByTimestampBefore(calendar.getTime())).isEqualTo(0);
    }

    @Test
    void whenSave_thenReturnSavedData() {
        Date now = new Date();
        double latitude = 2.0;
        double longitude = 3.0;

        Air air = new Air(latitude, longitude, now);
        Air result = this.airRepository.save(air);

        assertThat(result).isEqualTo(air);
        assertThat(air).isEqualTo(this.entityManager.find(Air.class, air.getId()));

    }


}