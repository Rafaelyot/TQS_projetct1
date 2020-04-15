package com.air_quality.backend.air_quality.temporal;


import com.air_quality.backend.auto_complete.City;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Calendar;
import java.util.Date;


import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class TemporalAirRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private TemporalAirRepository temporalAirRepository;


    @Test
    void whenGetTemporalAirByLatitudeAndLongitude_thenReturnAir() {
        double latitude = 1.0;
        double longitude = 1.0;
        int numberHours = 2;
        TemporalAir air = new TemporalAir(latitude, longitude, numberHours);
        this.entityManager.persistAndFlush(air);

        assertThat(this.temporalAirRepository.getTemporalAirByLatitudeAndLongitude(latitude, longitude)).isEqualTo(air);
    }

    @Test
    void whenGetTemporalAirByLatitudeAndLongitudeInvalid_thenReturnNull() {
        double latitude = 1.0;
        double longitude = 1.0;
        int numberHours = 2;
        TemporalAir air = new TemporalAir(latitude, longitude, numberHours);
        this.entityManager.persistAndFlush(air);

        assertThat(this.temporalAirRepository.getTemporalAirByLatitudeAndLongitude(0.0, 0.0)).isNull();

    }

    @Test
    void whenDeleteTemporalAirByTimestampBefore_thenReturnDeletion() {
        double latitude = 1.0;
        double longitude = 1.0;
        int numberHours = 2;
        TemporalAir air = new TemporalAir(latitude, longitude, numberHours);
        this.entityManager.persistAndFlush(air);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.SECOND, TemporalAir.TTL);

        assertThat(this.temporalAirRepository.deleteTemporalAirByTimestampBefore(calendar.getTime())).isEqualTo(1);
    }

    @Test
    void whenDeleteTemporalAirByTimestampBefore_thenReturnNoDeletion() {
        double latitude = 1.0;
        double longitude = 1.0;
        int numberHours = 2;
        TemporalAir air = new TemporalAir(latitude, longitude, numberHours);
        this.entityManager.persistAndFlush(air);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.SECOND, -(City.TTL + 60));

        assertThat(this.temporalAirRepository.deleteTemporalAirByTimestampBefore(calendar.getTime())).isEqualTo(0);
    }

    @Test
    void whenDeleteAir_thenReturnDeletion() {
        double latitude = 1.0;
        double longitude = 1.0;
        int numberHours = 2;
        TemporalAir air = new TemporalAir(latitude, longitude, numberHours);
        this.entityManager.persistAndFlush(air);

        this.temporalAirRepository.delete(air);

        assertThat(this.temporalAirRepository.findById(air.getId())).isEmpty();
        assertThat(this.entityManager.find(TemporalAir.class, air.getId())).isNull();

    }

    @Test
    void whenSave_thenReturnSavedData() {
        double latitude = 1.0;
        double longitude = 1.0;
        int numberHours = 2;
        TemporalAir air = new TemporalAir(latitude, longitude, numberHours);
        TemporalAir result =  this.temporalAirRepository.save(air);
        TemporalAir resultEntity = this.entityManager.find(TemporalAir.class, air.getId());

        assertThat(result).isEqualTo(air);
        assertThat(resultEntity).isEqualTo(air);

    }


}