package com.air_quality.backend.air_quality.current;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;


@Repository
@Transactional
public interface AirRepository extends JpaRepository<Air, Long> {

    public Air getAirByLatitudeAndLongitudeAndDate(double latitude, double longitude, Date date);

    public Long deleteAirByTimestampBefore(Date expiryDate);



}
