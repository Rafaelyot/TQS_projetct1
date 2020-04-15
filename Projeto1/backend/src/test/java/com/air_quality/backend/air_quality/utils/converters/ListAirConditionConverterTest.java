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
import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class ListAirConditionConverterTest {

    private ListAirConditionConverter listAirConditionConverter;
    private List<AirCondition> listAirConditions;


    @BeforeEach
    void setUp() {
        listAirConditionConverter = new ListAirConditionConverter();
        listAirConditions = this.listAirConditionSetter();
    }

    private List<AirCondition> listAirConditionSetter() {
        final BMHealthRecommendations bmHealthRecommendations = new BMHealthRecommendations();
        bmHealthRecommendations.setGeneralPopulation("population");
        final BMPollutants bmPollutants = new BMPollutants();
        BMPollutant co = new BMPollutant();
        co.setDisplayName("co");
        co.setFullName("co_full_name");
        bmPollutants.setCo(co);
        List<AirCondition> list = new ArrayList<>();
        list.add(new AirCondition(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime(), 0L, "category", "color", new AirPollutant("name", 0.0, "units"), bmPollutants, bmHealthRecommendations));
        return list;
    }

    @Test
    void testConvertToDatabaseColumn() throws IOException, JSONException {
        final String result = listAirConditionConverter.convertToDatabaseColumn(this.listAirConditions);
        JSONAssert.assertEquals(result, TestUtils.readJsonAsString("src/test/java/com/air_quality/backend/json_files/ListAirConditionConverterTestJson.json"), false);
    }

    @Test
    void testConvertToDatabaseColumnError() throws JsonProcessingException {
        ObjectMapper objectMapper = org.mockito.Mockito.mock(ObjectMapper.class);
        ListAirConditionConverter listAirConditionConverterError = new ListAirConditionConverter(objectMapper);
        when(objectMapper.writeValueAsString(any(List.class))).thenThrow(new JsonProcessingException("Test error") {
        });
        assertThat(listAirConditionConverterError.convertToDatabaseColumn(listAirConditions), is(nullValue()));
    }

    @Test
    void testConvertToEntityAttribute() throws IOException {
        final List<AirCondition> result = listAirConditionConverter.convertToEntityAttribute(TestUtils.readJsonAsString("src/test/java/com/air_quality/backend/json_files/ListAirConditionConverterTestJson.json"));
        TestUtils.assertComparingFieldValues(result, this.listAirConditions);
    }

    @Test
    void testConvertToEntityAttributeError() {
        String invalidJson = "[";
        assertThat(listAirConditionConverter.convertToEntityAttribute(invalidJson), is(new ArrayList<>()));
    }
}
