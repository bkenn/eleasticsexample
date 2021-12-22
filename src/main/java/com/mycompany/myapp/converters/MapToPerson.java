package com.mycompany.myapp.converters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.myapp.domain.Person;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.stereotype.Component;

@Component
@ReadingConverter
public class MapToPerson implements Converter<Map<String, Object>, Person> {

    private static final Logger logger = LoggerFactory.getLogger(MapToPerson.class);
    private ObjectMapper objectMapper;

    public MapToPerson(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public Person convert(Map<String, Object> source) {
        try {
            return objectMapper.readValue(objectMapper.writeValueAsString(source), Person.class);
        } catch (JsonProcessingException e) {
            logger.error("Cannot convert entity", e);
        }
        return null;
    }
}
