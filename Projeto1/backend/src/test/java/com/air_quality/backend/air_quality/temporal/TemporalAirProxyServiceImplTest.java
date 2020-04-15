package com.air_quality.backend.air_quality.temporal;

import com.air_quality.backend.TestUtils;
import com.air_quality.backend.air_quality.current.Air;
import com.air_quality.backend.air_quality.utils.features.AirCondition;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TemporalAirProxyServiceImplTest {

    @Mock(lenient = true)
    private TemporalAirRepository temporalAirRepository;

    @Mock(lenient = true)
    private StatisticsService statisticsService;

    @Mock(lenient = true)
    private TemporalAirService webservice;

    @InjectMocks
    private TemporalAirProxyServiceImpl temporalAirProxyService;


    private static final Date now = new Date();


    private List<AirCondition> airConditionsListSetter(int number) {
        assert number % 2 == 0 && number <= 6;
        List<AirCondition> airConditions = new ArrayList<>();
        airConditions.add(new AirCondition(now, 60, "Very Good", "Green plus", null, null, null));
        airConditions.add(new AirCondition(now, 50, "Good", "Green", null, null, null));
        airConditions.add(new AirCondition(now, 30, "Medium +", "Orange", null, null, null));
        airConditions.add(new AirCondition(now, 30, "Medium -", "Orange plus", null, null, null));
        airConditions.add(new AirCondition(now, 20, "Bad", "Red", null, null, null));
        airConditions.add(new AirCondition(now, 10, "Very Bad", "Red Plus", null, null, null));
        return airConditions.subList(0, number);
    }


    @BeforeEach
    void setUp() throws ParseException, IOException, URISyntaxException, CloneNotSupportedException {
        double latitude = 1.0;
        double longitude = 1.0;
        int numberHours = 2;
        TemporalAir air = new TemporalAir(latitude, longitude, numberHours);
        air.setTemporalAirConditions(this.airConditionsListSetter(numberHours * 2));

        TemporalAir air2 = new TemporalAir(latitude, longitude, 3);
        air2.setTemporalAirConditions(this.airConditionsListSetter(3 * 2));

        when(temporalAirRepository.getTemporalAirByLatitudeAndLongitude(latitude, longitude)).thenReturn(air);
        when(temporalAirRepository.getTemporalAirByLatitudeAndLongitude(0.0, 0.0)).thenReturn(null);
        when(temporalAirRepository.save(air2)).thenReturn(air2);
        when(webservice.getHistoryAirConditions(latitude, longitude, 3)).thenReturn(air2);
        when(webservice.getHistoryAirConditions(0.0, 0.0, 3)).thenReturn(air2);

    }


    @Test
    void whenGetHistoryAirConditionsSameHours_thenTemporalAirShouldBeFound() throws ParseException, IOException, URISyntaxException, CloneNotSupportedException {
        double latitude = 1.0;
        double longitude = 1.0;
        int numberHours = 2;
        TemporalAir air = new TemporalAir(latitude, longitude, numberHours);
        air.setTemporalAirConditions(this.airConditionsListSetter(numberHours * 2));

        TemporalAir result = this.temporalAirProxyService.getHistoryAirConditions(latitude, longitude, numberHours);
        TestUtils.assertComparingFieldValues(air, result);
        assertThat(result.getTemporalAirConditions().size()).isEqualTo(air.getTemporalAirConditions().size());

        verify(temporalAirRepository, VerificationModeFactory.times(1)).getTemporalAirByLatitudeAndLongitude(latitude, longitude);
        reset(temporalAirRepository);
    }

    @Test
    void whenGetHistoryAirConditionsLessHours_thenTemporalAirShouldBeFound() throws ParseException, IOException, URISyntaxException, CloneNotSupportedException {
        double latitude = 1.0;
        double longitude = 1.0;
        int numberHours = 1;
        TemporalAir air = new TemporalAir(latitude, longitude, numberHours);
        air.setTemporalAirConditions(this.airConditionsListSetter(2 * 2));

        TemporalAir result = this.temporalAirProxyService.getHistoryAirConditions(latitude, longitude, numberHours);

        int listSize = air.getTemporalAirConditions().size();
        int difference = (listSize / 2) - numberHours;
        int end = listSize - difference;

        assertThat(result.getTemporalAirConditions().size()).isEqualTo(air.getTemporalAirConditions().subList(difference, end).size());
        assertThat(result.getTemporalAirConditions().size()).isEqualTo(numberHours * 2);

        for (int i = 0; i < result.getTemporalAirConditions().size(); i++)
            TestUtils.assertComparingFieldValues(result.getTemporalAirConditions().get(i), air.getTemporalAirConditions().get(difference + i));


        verify(temporalAirRepository, VerificationModeFactory.times(1)).getTemporalAirByLatitudeAndLongitude(latitude, longitude);
        reset(temporalAirRepository);
    }

    @Test
    void whenGetHistoryAirConditionsMoreHours_thenTemporalAirShouldBeFound() throws ParseException, IOException, URISyntaxException, CloneNotSupportedException {
        double latitude = 1.0;
        double longitude = 1.0;
        int numberHours = 3;
        TemporalAir air = new TemporalAir(latitude, longitude, numberHours);
        air.setTemporalAirConditions(this.airConditionsListSetter(2 * 2));

        TemporalAir result = this.temporalAirProxyService.getHistoryAirConditions(latitude, longitude, 3);

        assertThat(result.getTemporalAirConditions().size()).isEqualTo(numberHours * 2);

        List<AirCondition> expected = this.airConditionsListSetter(6);

        for (int i = 0; i < result.getTemporalAirConditions().size(); i++)
            TestUtils.assertComparingFieldValues(result.getTemporalAirConditions().get(i), expected.get(i));


        verify(webservice, VerificationModeFactory.times(1)).getHistoryAirConditions(latitude, longitude, 3);
        reset(webservice);

        verify(temporalAirRepository, VerificationModeFactory.times(1)).getTemporalAirByLatitudeAndLongitude(latitude, longitude);
        reset(temporalAirRepository);
    }


    @Test
    void whenGetHistoryAirConditionsNoHours_thenTemporalAirShouldBeFound() throws ParseException, IOException, URISyntaxException, CloneNotSupportedException {
        double latitude = 1.0;
        double longitude = 1.0;
        int numberHours = 3;
        TemporalAir air = new TemporalAir(latitude, longitude, numberHours);
        air.setTemporalAirConditions(this.airConditionsListSetter(2 * 2));

        TemporalAir result = this.temporalAirProxyService.getHistoryAirConditions(latitude, longitude, 0);

        assertThat(result.getTemporalAirConditions().size()).isEqualTo(0);

        verify(temporalAirRepository, VerificationModeFactory.times(1)).getTemporalAirByLatitudeAndLongitude(latitude, longitude);
        reset(temporalAirRepository);
    }

    @Test
    void whenGetHistoryAirConditionsFirstTime_thenTemporalAirShouldBeFound() throws ParseException, IOException, URISyntaxException, CloneNotSupportedException {
        double latitude = 0.0;
        double longitude = 0.0;

        TemporalAir result = this.temporalAirProxyService.getHistoryAirConditions(latitude, longitude, 3);

        assertThat(result.getTemporalAirConditions().size()).isEqualTo(6);

        List<AirCondition> expected = this.airConditionsListSetter(6);

        for (int i = 0; i < result.getTemporalAirConditions().size(); i++)
            TestUtils.assertComparingFieldValues(result.getTemporalAirConditions().get(i), expected.get(i));

        verify(webservice, VerificationModeFactory.times(1)).getHistoryAirConditions(latitude, longitude, 3);
        reset(webservice);

        verify(temporalAirRepository, VerificationModeFactory.times(1)).getTemporalAirByLatitudeAndLongitude(latitude, longitude);
        reset(temporalAirRepository);
    }

    @Test
    void whenGetHistoryAirConditionsFirstTimeInvalidHours_thenTemporalAirShouldBeFound() throws ParseException, IOException, URISyntaxException, CloneNotSupportedException {
        double latitude = 0.0;
        double longitude = 0.0;

        TemporalAir result = this.temporalAirProxyService.getHistoryAirConditions(latitude, longitude, 0);

        assertThat(result.getTemporalAirConditions().size()).isEqualTo(0);

        verify(temporalAirRepository, VerificationModeFactory.times(1)).getTemporalAirByLatitudeAndLongitude(latitude, longitude);
        reset(temporalAirRepository);
    }

    @Test
    void whenGivenAir_saveAir() {
        double latitude = 1.0;
        double longitude = 1.0;
        int numberHours = 3;
        TemporalAir air = new TemporalAir(latitude, longitude, numberHours);
        air.setTemporalAirConditions(this.airConditionsListSetter(6));

        when(temporalAirRepository.save(air)).thenReturn(air);
        assertThat(temporalAirProxyService.save(air)).isEqualTo(air);

        verify(temporalAirRepository, VerificationModeFactory.times(1)).save(air);
        reset(temporalAirRepository);
    }


}