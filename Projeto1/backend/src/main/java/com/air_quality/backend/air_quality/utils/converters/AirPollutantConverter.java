package com.air_quality.backend.air_quality.utils.converters;


import com.air_quality.backend.air_quality.utils.features.AirPollutant;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.AttributeConverter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AirPollutantConverter implements AttributeConverter<AirPollutant, String> {

    private ObjectMapper objectMapper;
    private final Logger logger = Logger.getLogger(AirPollutantConverter.class.getName());

    public AirPollutantConverter() {
        this.objectMapper = new ObjectMapper();
    }

    public AirPollutantConverter(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;

    }

    @Override
    public String convertToDatabaseColumn(AirPollutant airPollutant) {
        try {
            return this.objectMapper.writeValueAsString(airPollutant);
        } catch (JsonProcessingException e) {
            logger.log(Level.WARNING, "Error on <{0}> : {1}", new Object[]{new Throwable().getStackTrace()[0].getMethodName(), e});
            return null;
        }
    }

    @Override
    public AirPollutant convertToEntityAttribute(String s) {
        try {
            return this.objectMapper.readValue(s, AirPollutant.class);
        } catch (IOException e) {
            logger.log(Level.WARNING, "Error on <{0}> : {1}", new Object[]{new Throwable().getStackTrace()[0].getMethodName(), e});
            return null;
        }
    }
}
