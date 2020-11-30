package com.nrha.reinersuite.models;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.LoggerFactory;

public abstract class AbstractEntity {

    @Override
    public String toString () {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(this);
        }
        catch (JsonProcessingException e) {
            LoggerFactory.getLogger(this.getClass()).error("Error while mapping object value to JSON string", e);
        }

        return null;

    }
}
