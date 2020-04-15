package com.air_quality.backend.auto_complete;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Repository
@Transactional
public interface CityRepository extends JpaRepository<City, Long> {

    public List<City> getCityByName(String name);

    public Long deleteCityByTimestampBefore(Date expiryDate);



}
