package com.air_quality.backend.auto_complete;

import com.air_quality.backend.utils.Serializer;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CityServiceImplTest {


    @Mock(lenient = true)
    private Serializer serializer;

    @InjectMocks
    private CityServiceImpl cityService;


    @BeforeEach
    void setUp() throws ParseException, IOException, URISyntaxException {
        City aveiro = new City(1, "Aveiro", "Aveiro Portugal", 2.0, 2.0);
        City lisboa = new City(1, "Lisboa", "Lisboa Portugal", 4.0, 4.0);

        when(serializer.serializeCities(aveiro.getName())).thenReturn(Arrays.asList(aveiro));
        when(serializer.serializeCities("no city")).thenReturn(new ArrayList<>());
    }

    @Test
    void whenGetCityByName_thenCityShouldBeFound() throws ParseException, IOException, URISyntaxException {
        String cityName = "Aveiro";
        List<City> result = cityService.getCityName(cityName);
        assertThat(result.get(0).getName()).isEqualTo(cityName);

        verify(serializer, VerificationModeFactory.times(1)).serializeCities(cityName);
        reset(serializer);
    }

    @Test
    void whenGetCityByNameInvalid_thenCityShouldNotBeFound() throws ParseException, IOException, URISyntaxException {
        String cityName = "no city";
        List<City> result = cityService.getCityName(cityName);
        assertThat(result).hasSize(0);

        verify(serializer, VerificationModeFactory.times(1)).serializeCities(cityName);
        reset(serializer);
    }


    @Test
    void whenGivenCities_returnAll() {
        City aveiro = new City(1, "Aveiro", "Aveiro Portugal", 2.0, 2.0);
        City lisboa = new City(1, "Lisboa", "Lisboa Portugal", 4.0, 4.0);
        List<City> cities = Arrays.asList(aveiro, lisboa);
        List<City> result = this.cityService.saveAll(cities);
        for (int i = 0; i < cities.size(); i++)
            assertThat(cities.get(i).getFullName()).isEqualTo(result.get(i).getFullName());


    }
}
