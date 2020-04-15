package com.air_quality.backend.air_quality.utils.converters;

import com.air_quality.backend.air_quality.utils.features.AirCondition;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.AttributeConverter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AirConditionConverter implements AttributeConverter<AirCondition, String> {

    private ObjectMapper objectMapper;
    private final Logger logger = Logger.getLogger(AirConditionConverter.class.getName());

    public AirConditionConverter() {
        this.objectMapper = new ObjectMapper();
    }

    public AirConditionConverter(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public String convertToDatabaseColumn(AirCondition airCondition) {
        try {
            return this.objectMapper.writeValueAsString(airCondition);
        } catch (JsonProcessingException e) {
            logger.log(Level.WARNING, "Error on <{0}> : {1}", new Object[]{new Throwable().getStackTrace()[0].getMethodName(), e});
            return null;
        }
    }

    @Override
    public AirCondition convertToEntityAttribute(String s) {
        try {
            return this.objectMapper.readValue(s, AirCondition.class);
        } catch (IOException e) {
            logger.log(Level.WARNING, "Error on <{0}> : {1}", new Object[]{new Throwable().getStackTrace()[0].getMethodName(), e});
            return null;
        }
    }
}
