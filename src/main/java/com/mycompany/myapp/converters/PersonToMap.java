package com.mycompany.myapp.converters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.myapp.domain.Person;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.stereotype.Component;

@Component
@WritingConverter
public class PersonToMap implements Converter<Person, Map<String, Object>> {

    private static final Logger logger = LoggerFactory.getLogger(PersonToMap.class);

    private final ObjectMapper objectMapper;

    PersonToMap(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public Map<String, Object> convert(Person source) {
        try {
            return objectMapper.readValue(objectMapper.writeValueAsString(source), Map.class);
        } catch (JsonProcessingException e) {
            logger.error("Cannot convert entity", e);
        }

        return null;
    }
}
