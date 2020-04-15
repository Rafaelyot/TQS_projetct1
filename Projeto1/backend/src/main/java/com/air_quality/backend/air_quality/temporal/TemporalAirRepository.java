package com.air_quality.backend.air_quality.temporal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;


@Repository
@Transactional
public interface TemporalAirRepository extends JpaRepository<TemporalAir, Long> {

    public TemporalAir getTemporalAirByLatitudeAndLongitude(double latitude, double longitude);

    public Long deleteTemporalAirByTimestampBefore(Date expiryDate);
}
