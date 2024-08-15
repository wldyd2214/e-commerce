package com.hhplus.commerce.spring.api.order.model.convert;

import com.hhplus.commerce.spring.api.order.model.type.EventType;
import jakarta.persistence.AttributeConverter;

import java.util.EnumSet;
import java.util.NoSuchElementException;

public class EventTypeConverter implements AttributeConverter<EventType, String> {

    @Override
    public String convertToDatabaseColumn(EventType attribute) {
        return attribute.name();
    }

    @Override
    public EventType convertToEntityAttribute(String dbData) {
        return EnumSet.allOf(EventType.class).stream()
                      .filter(e -> e.name().equals(dbData))
                      .findAny()
                      .orElseThrow(NoSuchElementException::new);
    }
}
