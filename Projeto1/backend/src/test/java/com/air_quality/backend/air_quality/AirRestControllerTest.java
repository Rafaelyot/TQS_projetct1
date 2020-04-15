package com.air_quality.backend.air_quality;


import com.air_quality.backend.air_quality.current.Air;
import com.air_quality.backend.air_quality.current.AirService;
import com.air_quality.backend.air_quality.temporal.TemporalAir;
import com.air_quality.backend.air_quality.temporal.TemporalAirService;
import com.air_quality.backend.air_quality.utils.features.AirCondition;

import org.junit.jupiter.api.Test;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;


import java.io.IOException;

import java.util.*;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.springframework.http.MediaType;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(AirRestController.class)
class AirRestControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private AirService airService;

    @MockBean
    private TemporalAirService temporalAirService;

    private static final Date now = new Date();

    @Test
    void givenAir_whenGetCurrentCondition_thenReturnJSONObject() throws Exception {
        double latitude = 1.0;
        double longitude = 1.0;

        Air air = new Air(latitude, longitude, now);
        air.setCurrentAirCondition(new AirCondition(now, 60, "Good", "Green", null, null, null));

        given(airService.getCurrentAirConditions(latitude, longitude)).willReturn(air);

        mvc.perform(get(String.format("/air/lat=%s&long=%s", latitude, longitude)).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).
                andExpect(jsonPath("$.latitude", is(latitude))).andExpect(jsonPath("$.longitude", is(longitude))).
                andExpect(jsonPath("$.current_conditions.category", is("Good"))).
                andExpect(jsonPath("$.current_conditions.color", is("Green")));


        verify(airService, VerificationModeFactory.times(1)).getCurrentAirConditions(latitude, longitude);
        reset(airService);

    }


    @Test
    void givenAir_whenGetCurrentConditionInvalid_thenReturnError() throws Exception {
        double latitude = -1.0;
        double longitude = -1.0;

        given(airService.getCurrentAirConditions(latitude, longitude)).willThrow(IOException.class);

        mvc.perform(get(String.format("/air/lat=%s&long=%s", latitude, longitude)).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isInternalServerError());

        verify(airService, VerificationModeFactory.times(1)).getCurrentAirConditions(latitude, longitude);
        reset(airService);

    }


    @Test
    void givenTemporalAir_whenGetHistory_thenReturnJSONObject() throws Exception {
        double latitude = 1.0;
        double longitude = 1.0;
        int numberHours = 1;
        TemporalAir air = new TemporalAir(latitude, longitude, numberHours);
        List<AirCondition> airConditionList = new ArrayList<>();
        airConditionList.add(new AirCondition(now, 60, "Good", "Green", null, null, null));
        air.setTemporalAirConditions(airConditionList);

        given(temporalAirService.getHistoryAirConditions(latitude, longitude, numberHours)).willReturn(air);

        mvc.perform(get(String.format("/air/history/lat=%s&long=%s&hours=%s", latitude, longitude, numberHours)).contentType(MediaType.APPLICATION_JSON)).
                andExpect(status().isOk()).andExpect(jsonPath("$.latitude", is(latitude))).
                andExpect(jsonPath("$.longitude", is(longitude))).andExpect(jsonPath("$.number_hours", is(1))).
                andExpect(jsonPath("$.temporal_conditions[0].category", is("Good"))).
                andExpect(jsonPath("$.temporal_conditions[0].color", is("Green")));

        verify(temporalAirService, VerificationModeFactory.times(1)).getHistoryAirConditions(latitude, longitude, numberHours);
        reset(temporalAirService);
    }

    @Test
    void givenTemporalAir_whenGetHistoryInvalid_thenReturnError() throws Exception {
        double latitude = -1.0;
        double longitude = -1.0;
        int numberHours = -1;

        given(temporalAirService.getHistoryAirConditions(latitude, longitude, numberHours)).willThrow(IOException.class);

        mvc.perform(get(String.format("/air/history/lat=%s&long=%s&hours=%s", latitude, longitude, numberHours)).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isInternalServerError());

        verify(temporalAirService, VerificationModeFactory.times(1)).getHistoryAirConditions(latitude, longitude, numberHours);
        reset(temporalAirService);

    }

    @Test
    void givenAir_whenGetAirByDate_thenReturnJSONObject() throws Exception {
        double latitude = 1.0;
        double longitude = 1.0;
        long dateSeconds = 1586818800000L;
        Date date = new GregorianCalendar(2020, Calendar.APRIL, 6, 1, 0).getTime();
        Air air = new Air(latitude, longitude, date);
        air.setCurrentAirCondition(new AirCondition(date, 60, "Good", "Green", null, null, null));

        given(airService.getAirConditionByDate(latitude, longitude, dateSeconds)).willReturn(air);

        mvc.perform(get(String.format("/air/by-date/lat=%s&long=%s&datetime=%s", latitude, longitude, dateSeconds)).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).
                andExpect(jsonPath("$.latitude", is(latitude))).andExpect(jsonPath("$.longitude", is(longitude))).
                andExpect(jsonPath("$.current_conditions.category", is("Good"))).
                andExpect(jsonPath("$.current_conditions.color", is("Green")));


        verify(airService, VerificationModeFactory.times(1)).getAirConditionByDate(latitude, longitude, dateSeconds);
        reset(airService);

    }


    @Test
    void givenAir_whenGetAirByDateInvalid_thenReturnError() throws Exception{
        double latitude = -1.0;
        double longitude = -1.0;
        long dateSeconds = -1L;

        given(airService.getAirConditionByDate(latitude, longitude, dateSeconds)).willThrow(IOException.class);

        mvc.perform(get(String.format("/air/by-date/lat=%s&long=%s&datetime=%s", latitude, longitude, dateSeconds)).contentType(MediaType.APPLICATION_JSON)).
                andExpect(status().isInternalServerError());
    }


}