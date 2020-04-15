package com.air_quality.backend.auto_complete;

import com.air_quality.backend.cache_statistics.StatisticsService;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.verification.VerificationModeFactory;
import org.mockito.junit.jupiter.MockitoExtension;


import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CityProxyServiceImplTest {

    @Mock( lenient = true)
    private CityRepository cityRepository;

    @Mock( lenient = true)
    private StatisticsService statisticsService;

    @Mock( lenient = true)
    private CityService webService;

    @InjectMocks
    private CityProxyServiceImpl cityService;


    @BeforeEach
    void setUp() throws ParseException, IOException, URISyntaxException {
        City aveiro = new City(1, "Aveiro", "Aveiro Portugal", 2.0, 2.0);
        City lisboa = new City(1, "Lisboa", "Lisboa Portugal", 4.0, 4.0);

        when(this.cityRepository.getCityByName(aveiro.getName())).thenReturn(Arrays.asList(aveiro));
        when(this.cityRepository.getCityByName(lisboa.getName())).thenReturn(Arrays.asList(lisboa));
        when(this.cityRepository.getCityByName("no city")).thenReturn(new ArrayList<>());
        when(this.webService.getCityName("no city")).thenReturn(new ArrayList<>());

    }

    @Test
    void whenGetCityByName_thenCityShouldBeFound() throws ParseException, IOException, URISyntaxException {
        String cityName = "Aveiro";
        List<City> result = cityService.getCityName(cityName);
        assertThat(result.get(0).getName()).isEqualTo(cityName);

        verify(cityRepository, VerificationModeFactory.times(1)).getCityByName(cityName);
        reset(cityRepository);
    }

    @Test
    void whenGetCityByNameInvalid_thenCityShouldNotBeFound() throws ParseException, IOException, URISyntaxException {
        String cityName = "no city";
        List<City> result = cityService.getCityName(cityName);
        assertThat(result).hasSize(0);

        verify(cityRepository, VerificationModeFactory.times(1)).getCityByName(cityName);
        reset(cityRepository);
    }

    @Test
    void whenGetCityByNameIsSmall_thenShouldReturnError() {
        String cityName = "no";
        assertThatThrownBy(()->{
            cityService.getCityName(cityName);
        }).isInstanceOf(IOException.class).hasMessageContaining("O parametro 'name' deve ter pelo menos 3 caracteres");

    }

    @Test
    void whenGivenCities_saveAll() {
        City aveiro = new City(1, "Aveiro", "Aveiro Portugal", 2.0, 2.0);
        City lisboa = new City(1, "Lisboa", "Lisboa Portugal", 4.0, 4.0);

        when(this.cityRepository.saveAll(anyIterable())).thenReturn(Arrays.asList(aveiro,lisboa));

        List<City> cities = Arrays.asList(aveiro,lisboa);
        List<City> result = this.cityService.saveAll(cities);
        for(int i = 0 ; i< cities.size() ; i++)
            assertThat(cities.get(i).getFullName()).isEqualTo(result.get(i).getFullName());


        verify(cityRepository, VerificationModeFactory.times(1)).saveAll(anyIterable());
        reset(cityRepository);

    }



}
