package com.air_quality.backend.air_quality.utils.converters;

import com.air_quality.backend.air_quality.utils.features.AirCondition;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


import javax.persistence.AttributeConverter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ListAirConditionConverter implements AttributeConverter<List<AirCondition>, String> {

    private ObjectMapper objectMapper;
    private final Logger logger = Logger.getLogger(ListAirConditionConverter.class.getName());

    public ListAirConditionConverter() {
        this.objectMapper = new ObjectMapper();
    }

    public ListAirConditionConverter(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public String convertToDatabaseColumn(List<AirCondition> airConditions) {
        try {
            return this.objectMapper.writeValueAsString(airConditions);
        } catch (JsonProcessingException e) {
            logger.log(Level.WARNING, "Error on <{0}> : {1}", new Object[]{new Throwable().getStackTrace()[0].getMethodName(), e});
            return null;
        }
    }

    @Override
    public List<AirCondition> convertToEntityAttribute(String s) {
        List<AirCondition> airConditions = new ArrayList<>();
        try {
            airConditions = this.objectMapper.readValue(s, new TypeReference<List<AirCondition>>() {
            });
        } catch (IOException e) {
            logger.log(Level.WARNING, "Error on <{0}> : {1}", new Object[]{new Throwable().getStackTrace()[0].getMethodName(), e});
        }
        return airConditions;
    }
}
