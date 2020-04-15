package com.air_quality.backend.auto_complete;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
class CityRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CityRepository cityRepository;

    private List<City> citiesSetter() {
        List<City> cities = new ArrayList<>();
        cities.add(new City(1, "Aveiro", "Aveiro Portugal", 2.0, 2.0));
        cities.add(new City(2, "Aveiro", "Aveiro2 Portugal", 10.0, 10.0));
        return cities;
    }

    private void persistAndFlushList(List<City> cities) {
        for (City c : cities)
            entityManager.persistAndFlush(c);
    }

    @Test
    void whenGetCityByName_thenReturnCity() {
        List<City> cities = this.citiesSetter();
        this.persistAndFlushList(cities);

        List<City> foundCities = this.cityRepository.getCityByName("Aveiro");
        assertThat(foundCities).isEqualTo(foundCities);
    }

    @Test
    void whenGetCityByNameInvalidName_thenReturnEmpty() {
        List<City> cities = this.citiesSetter();
        this.persistAndFlushList(cities);

        List<City> foundCities = this.cityRepository.getCityByName("Lisboa");
        assertThat(foundCities).hasSize(0);
    }

    @Test
    void whenGetCityByID_thenReturnCity() {
        List<City> cities = this.citiesSetter();
        this.persistAndFlushList(cities);

        City expected = cities.get(0);
        City city = this.cityRepository.findById(expected.getId()).orElse(null);
        assertThat(city).isEqualTo(expected);
    }

    @Test
    void whenGetCityByIDInvalidID_thenReturnNull() {
        List<City> cities = this.citiesSetter();
        this.persistAndFlushList(cities);

        City city = this.cityRepository.findById(-1L).orElse(null);
        assertThat(city).isNull();
    }

    @Test
    void whenDeleteCityByTimestampBefore_thenReturnDeletion() {
        List<City> cities = this.citiesSetter();
        this.persistAndFlushList(cities);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.SECOND, City.TTL);

        assertThat(this.cityRepository.deleteCityByTimestampBefore(calendar.getTime())).isEqualTo(cities.size());
    }

    @Test
    void whenDeleteCityByTimestampBefore_thenReturnNoDeletion() {
        List<City> cities = this.citiesSetter();
        this.persistAndFlushList(cities);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.SECOND, -(City.TTL + 60));

        assertThat(this.cityRepository.deleteCityByTimestampBefore(calendar.getTime())).isEqualTo(0);
    }

    @Test
    void whenSaveAll_thenReturnSavedData() {
        List<City> cities = this.cityRepository.saveAll(this.citiesSetter());
        List<City> results = this.cityRepository.findAll();

        assertThat(cities).isEqualTo(results);
        for (int i = 0; i < cities.size(); i++)
            assertThat(cities.get(i)).isEqualTo(this.entityManager.find(City.class, cities.get(i).getId()));

    }


}