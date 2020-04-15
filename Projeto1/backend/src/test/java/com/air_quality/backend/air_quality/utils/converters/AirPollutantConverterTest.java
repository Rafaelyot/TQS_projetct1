package com.air_quality.backend.air_quality.utils.converters;

import com.air_quality.backend.TestUtils;
import com.air_quality.backend.air_quality.utils.features.AirPollutant;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.skyscreamer.jsonassert.JSONAssert;

import java.io.IOException;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class AirPollutantConverterTest {

    @Mock
    private AirPollutantConverter airPollutantConverter;

    private AirPollutant airPollutant;


    @BeforeEach
    void setUp() {
        airPollutantConverter = new AirPollutantConverter();
        airPollutant = new AirPollutant("name", 1.0, "units");
    }


    @Test
    void testConvertToDatabaseColumn() throws IOException, JSONException {
        final String result = airPollutantConverter.convertToDatabaseColumn(this.airPollutant);
        JSONAssert.assertEquals(result, TestUtils.readJsonAsString("src/test/java/com/air_quality/backend/json_files/AirPollutantConverterTestJson.json"), false);
    }

    @Test
    void testConvertToDatabaseColumnError() throws JsonProcessingException {
        ObjectMapper objectMapper = org.mockito.Mockito.mock(ObjectMapper.class);
        AirPollutantConverter airPollutantConverterError = new AirPollutantConverter(objectMapper);
        when(objectMapper.writeValueAsString(any(AirPollutant.class))).thenThrow(new JsonProcessingException("Test Error") {
        });
        assertThat(airPollutantConverterError.convertToDatabaseColumn(this.airPollutant), is(nullValue()));
    }

    @Test
    void testConvertToEntityAttribute() throws IOException {
        final AirPollutant result = airPollutantConverter.convertToEntityAttribute(TestUtils.readJsonAsString("src/test/java/com/air_quality/backend/json_files/AirPollutantConverterTestJson.json"));
        TestUtils.assertComparingFieldValues(result, this.airPollutant);
    }

    @Test
    void testConvertToEntityAttributeError() {
        String invalidJson = "[";
        assertThat(airPollutantConverter.convertToEntityAttribute(invalidJson), is(nullValue()));
    }

}
