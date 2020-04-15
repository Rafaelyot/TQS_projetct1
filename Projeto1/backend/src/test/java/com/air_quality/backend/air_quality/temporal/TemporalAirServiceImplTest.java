package com.air_quality.backend.air_quality.temporal;

import com.air_quality.backend.TestUtils;
import com.air_quality.backend.air_quality.utils.features.AirCondition;
import com.air_quality.backend.utils.Serializer;
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
class TemporalAirServiceImplTest {

    @Mock(lenient = true)
    private Serializer serializer;

    @InjectMocks
    private TemporalAirServiceImpl service;

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
    void setUp() throws ParseException, IOException, URISyntaxException {
        double latitude = 1.0;
        double longitude = 1.0;
        int numberHours = 2;
        List<AirCondition> airConditions = this.airConditionsListSetter(numberHours * 2);

        when(serializer.serializeTemporal(latitude, longitude, numberHours)).thenReturn(airConditions);
        when(serializer.serializeTemporal(0.0, 0.0, 0)).thenReturn(null);

    }

    @Test
    void whenGetHistoryAirConditions_thenTemporalAirShouldBeFound() throws ParseException, IOException, URISyntaxException {
        double latitude = 1.0;
        double longitude = 1.0;
        int numberHours = 2;
        TemporalAir air = new TemporalAir(latitude, longitude, numberHours);
        air.setTemporalAirConditions(this.airConditionsListSetter(4));

        TestUtils.assertComparingFieldValues(service.getHistoryAirConditions(latitude, longitude, numberHours), air);

        verify(serializer, VerificationModeFactory.times(1)).serializeTemporal(latitude, longitude, numberHours);
        reset(serializer);
    }


    @Test
    void whenGetHistoryAirConditionsInvalid_thenTemporalAirShouldBeFound() throws ParseException, IOException, URISyntaxException {
        double latitude = 0.0;
        double longitude = 0.0;
        int numberHours = 0;

        assertThat(service.getHistoryAirConditions(latitude, longitude, numberHours).getTemporalAirConditions()).isNull();

        verify(serializer, VerificationModeFactory.times(1)).serializeTemporal(latitude, longitude, numberHours);
        reset(serializer);
    }

    @Test
    void whenGivenAir_returnAir() {
        double latitude = 1.0;
        double longitude = 1.0;
        int numberHours = 3;
        TemporalAir air = new TemporalAir(latitude, longitude, numberHours);
        air.setTemporalAirConditions(this.airConditionsListSetter(6));

        assertThat(service.save(air)).isEqualTo(air);

    }


}