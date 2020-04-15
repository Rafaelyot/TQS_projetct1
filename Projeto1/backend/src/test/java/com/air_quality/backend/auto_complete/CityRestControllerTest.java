package com.air_quality.backend.auto_complete;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CityRestController.class)
public class CityRestControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private CityService service;


    @Test
    void givenCities_whenGetCityByName_thenReturnJSONArray() throws Exception {
        City aveiro = new City(1, "Aveiro", "Aveiro Portugal", 2.0, 2.0);
        City aveiro2 = new City(2, "Aveiro2", "Aveiro2 Portugal", 4.0, 4.0);
        List<City> cities = Arrays.asList(aveiro, aveiro2);

        given(service.getCityName(Mockito.anyString())).willReturn(cities);

        mvc.perform(get("/cities/Aveiro").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).
                andExpect(jsonPath("$", hasSize(2))).andExpect(jsonPath("$[0].full_name", is(aveiro.getFullName()))).
                andExpect(jsonPath("$[1].full_name", is(aveiro2.getFullName())));

        verify(service, VerificationModeFactory.times(1)).getCityName(Mockito.any());
        reset(service);

    }


    @Test
    void givenCities_whenGetCityByNameSmall_thenReturnJSONError() throws Exception {
        given(service.getCityName("Av")).willThrow(IOException.class);
        mvc.perform(get("/cities/Av").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isInternalServerError());

        verify(service, VerificationModeFactory.times(1)).getCityName("Av");
        reset(service);
    }

}
