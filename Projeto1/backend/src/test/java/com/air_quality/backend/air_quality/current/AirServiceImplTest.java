package com.air_quality.backend.air_quality.current;

import com.air_quality.backend.TestUtils;
import com.air_quality.backend.air_quality.utils.features.AirCondition;
import com.air_quality.backend.utils.DateTools;
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
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AirServiceImplTest {


    @Mock(lenient = true)
    private Serializer serializer;

    @InjectMocks
    private AirServiceImpl airService;

    private static final Date now = new Date();
    private static final Date date = new GregorianCalendar(2020, Calendar.APRIL, 6, 0, 0).getTime();

    @BeforeEach
    void setUp() throws ParseException, IOException, URISyntaxException {
        Air air = new Air(0.0, 0.0, now);
        AirCondition airCondition = new AirCondition(now, 50, "Good", "red", null, null, null);
        AirCondition airCondition2 = new AirCondition(now, 40, "Bad", "blue", null, null, null);

        when(serializer.serializeCurrent(1.0, 1.0)).thenReturn(airCondition);
        when(serializer.serializeCurrent(0.0, 0.0)).thenReturn(null);
        when(serializer.serializeAirByDate(2.0, 2.0, "2020-04-06T00:00:00", "historical")).thenReturn(airCondition2);
        when(serializer.serializeAirByDate(eq(0.0), eq(0.0), any(String.class), eq("historical"))).thenReturn(null);

    }

    @Test
    void whenGetCurrentAirConditions_thenAirShouldBeFound() throws URISyntaxException, ParseException, IOException {
        Air air = new Air(1.0, 1.0, now);
        Air result = this.airService.getCurrentAirConditions(1.0, 1.0);
        assertThat(result.getLatitude()).isEqualTo(air.getLatitude());
        assertThat(result.getLongitude()).isEqualTo(air.getLongitude());


        verify(serializer, VerificationModeFactory.times(1)).serializeCurrent(1.0, 1.0);
        reset(serializer);

    }

    @Test
    void whenGetCurrentAirConditionsInvalid_thenAirShouldNotBeFound() throws URISyntaxException, ParseException, IOException {
        assertThat(this.airService.getCurrentAirConditions(0.0, 0.0).getCurrentAirCondition()).isNull();

        verify(serializer, VerificationModeFactory.times(1)).serializeCurrent(0.0, 0.0);
        reset(serializer);
    }

    @Test
    void whenGetAirConditionByDate_thenAirShouldBeFound() throws ParseException, IOException, URISyntaxException {
        long dateSeconds = 1586131200000L;
        double latitude = 2.0;
        double longitude = 2.0;
        Air air = new Air(latitude, longitude, date);
        AirCondition airCondition2 = new AirCondition(now, 40, "Bad", "blue", null, null, null);
        air.setCurrentAirCondition(airCondition2);
        Air result = this.airService.getAirConditionByDate(latitude, longitude, dateSeconds);

        TestUtils.assertComparingFieldValues(air, result);


        verify(serializer, VerificationModeFactory.times(1)).serializeAirByDate(2.0, 2.0, "2020-04-06T00:00:00", "historical");
        reset(serializer);

    }

    @Test
    void whenGetAirConditionByDateInvalid_thenAirShouldNotBeFound() throws ParseException, IOException, URISyntaxException {
        long dateSeconds = 1586131200000L;
        assertThat(this.airService.getAirConditionByDate(0.0, 0.0, dateSeconds).getCurrentAirCondition()).isNull();

        verify(serializer, VerificationModeFactory.times(1)).serializeAirByDate(eq(0.0), eq(0.0), any(String.class), eq("historical"));
        reset(serializer);

    }

    @Test
    void whenGivenCity_save() {
        double latitude = 0.0;
        double longitude = 0.0;
        Air air = new Air(latitude, longitude, now);

        Air result = this.airService.save(air);
        assertThat(result).isEqualTo(air);

    }


}