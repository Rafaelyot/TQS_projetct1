package com.air_quality.backend.air_quality.utils.converters;

import com.air_quality.backend.TestUtils;
import com.air_quality.backend.air_quality.utils.features.AirCondition;
import com.air_quality.backend.air_quality.utils.features.AirPollutant;
import com.air_quality.backend.utils.breezometer_serializers.BMHealthRecommendations;
import com.air_quality.backend.utils.breezometer_serializers.BMPollutant;
import com.air_quality.backend.utils.breezometer_serializers.BMPollutants;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;


import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class AirConditionConverterTest {

    private AirConditionConverter airConditionConverter;
    private AirCondition airCondition;

    @BeforeEach
    void setUp() {
        airConditionConverter = new AirConditionConverter();
        airCondition = this.airConditionSetter();
    }

    private AirCondition airConditionSetter() {
        final BMHealthRecommendations bmHealthRecommendations = new BMHealthRecommendations();
        bmHealthRecommendations.setGeneralPopulation("population");
        final BMPollutants bmPollutants = new BMPollutants();
        BMPollutant co = new BMPollutant();
        co.setDisplayName("co");
        co.setFullName("co_full_name");
        bmPollutants.setCo(co);
        return new AirCondition(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), 0L, "category", "color", new AirPollutant("name", 0.0, "units"), bmPollutants, bmHealthRecommendations);
    }

    @Test
    void testConvertToDatabaseColumn() throws IOException, JSONException {
        final String result = airConditionConverter.convertToDatabaseColumn(this.airCondition);
        JSONAssert.assertEquals(result, TestUtils.readJsonAsString("src/test/java/com/air_quality/backend/json_files/AirConditionConverterTestJson.json"), false);
    }

    @Test
    void testConvertToDatabaseColumnError() throws JsonProcessingException {
        ObjectMapper objectMapper = org.mockito.Mockito.mock(ObjectMapper.class);
        AirConditionConverter airConditionConverterError = new AirConditionConverter(objectMapper);
        when(objectMapper.writeValueAsString(any(AirCondition.class))).thenThrow(new JsonProcessingException("Test Error") {
        });
        assertThat(airConditionConverterError.convertToDatabaseColumn(this.airCondition), is(nullValue()));
    }

    @Test
    void testConvertToEntityAttribute() throws IOException {
        final AirCondition result = airConditionConverter.convertToEntityAttribute(TestUtils.readJsonAsString("src/test/java/com/air_quality/backend/json_files/AirConditionConverterTestJson.json"));
        TestUtils.assertComparingFieldValues(result, this.airCondition);
    }

    @Test
    void testConvertToEntityAttributeError() {
        String invalidJson = "[";
        assertThat(airConditionConverter.convertToEntityAttribute(invalidJson), is(nullValue()));
    }
}
