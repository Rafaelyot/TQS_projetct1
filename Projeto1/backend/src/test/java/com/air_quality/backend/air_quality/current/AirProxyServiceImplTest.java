package com.air_quality.backend.air_quality.current;

import com.air_quality.backend.TestUtils;
import com.air_quality.backend.cache_statistics.StatisticsService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.internal.verification.VerificationModeFactory;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.*;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AirProxyServiceImplTest {

    @Mock(lenient = true)
    private AirRepository airRepository;

    @Mock(lenient = true)
    private StatisticsService statisticsService;

    @Mock(lenient = true)
    private AirService webservice;

    @InjectMocks
    private AirProxyServiceImpl airService;

    private static final Date now = new Date();
    private static final Date date = new GregorianCalendar(2020, Calendar.APRIL, 6, 0, 0).getTime();

    @BeforeEach
    void setUp() throws IOException, ParseException, org.json.simple.parser.ParseException, URISyntaxException {
        Air air = new Air(1.0, 1.0, now);
        Air air2 = new Air(2.0, 2.0, date);


        when(this.airRepository.getAirByLatitudeAndLongitudeAndDate(eq(air.getLatitude()), eq(air.getLongitude()), any(Date.class))).thenReturn(air);
        when(this.airRepository.getAirByLatitudeAndLongitudeAndDate(air2.getLatitude(), air2.getLongitude(), date)).thenReturn(air2);
        when(this.airRepository.getAirByLatitudeAndLongitudeAndDate(eq(0.0), eq(0.0), any(Date.class))).thenReturn(null);
        when(this.webservice.getCurrentAirConditions(0.0, 0.0)).thenReturn(null);
        when(this.webservice.getAirConditionByDate(eq(0.0), eq(0.0), any(long.class))).thenReturn(null);
    }

    @Test
    void whenGetCurrentAirConditions_thenAirShouldBeFound() throws URISyntaxException, ParseException, org.json.simple.parser.ParseException, IOException {
        double latitude = 1.0;
        double longitude = 1.0;

        Air air = new Air(latitude, longitude, now);
        Air result = this.airService.getCurrentAirConditions(latitude, longitude);
        TestUtils.assertComparingFieldValues(air, result);

        verify(airRepository, VerificationModeFactory.times(1)).getAirByLatitudeAndLongitudeAndDate(eq(1.0), eq(1.0), any(Date.class));
        reset(airRepository);
    }

    @Test
    void whenGetCurrentAirConditionsInvalid_thenAirShouldNotBeFound() throws URISyntaxException, ParseException, org.json.simple.parser.ParseException, IOException {
        Air result = this.airService.getCurrentAirConditions(0.0, 0.0);
        assertThat(result).isNull();

        verify(airRepository, VerificationModeFactory.times(1)).getAirByLatitudeAndLongitudeAndDate(eq(0.0), eq(0.0), any(Date.class));
        reset(airRepository);
    }

    @Test
    void whenGetAirConditionByDate_thenAirShouldBeFound() throws ParseException, IOException, URISyntaxException {
        long seconds = 1586131200000L;
        double latitude = 2.0;
        double longitude = 2.0;

        Air air = new Air(latitude, longitude, date);
        TestUtils.assertComparingFieldValues(this.airService.getAirConditionByDate(latitude, longitude, seconds), air);

        verify(airRepository, VerificationModeFactory.times(1)).getAirByLatitudeAndLongitudeAndDate(latitude, longitude, date);
        reset(airRepository);
    }

    @Test
    void whenGetAirConditionByDateInvalid_thenAirShouldNotBeFound() throws ParseException, IOException, URISyntaxException {
        long seconds = 1586131200000L;
        double latitude = 0.0;
        double longitude = 0.0;

        assertThat(this.airService.getAirConditionByDate(latitude, longitude, seconds)).isNull();

        verify(airRepository, VerificationModeFactory.times(1)).getAirByLatitudeAndLongitudeAndDate(eq(0.0), eq(0.0), any(Date.class));
        reset(airRepository);
    }


    @Test
    void whenGivenCity_save() {
        double latitude = 2.0;
        double longitude = 2.0;
        Air air = new Air(latitude, longitude, now);

        when(this.airRepository.save(any(Air.class))).thenReturn(air);

        Air result = this.airService.save(air);
        assertThat(result).isEqualTo(air);

        verify(airRepository, VerificationModeFactory.times(1)).save(any(Air.class));
        reset(airRepository);
    }


}