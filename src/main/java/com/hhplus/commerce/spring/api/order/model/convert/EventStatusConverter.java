package com.hhplus.commerce.spring.api.order.model.convert;

import com.hhplus.commerce.spring.api.order.model.type.EventStatus;
import jakarta.persistence.AttributeConverter;
import org.springframework.stereotype.Component;

import java.util.EnumSet;
import java.util.NoSuchElementException;

@Component
public class EventStatusConverter implements AttributeConverter<EventStatus, String> {

    @Override
    public String convertToDatabaseColumn(EventStatus attribute) {
        return attribute.name();
    }

    @Override
    public EventStatus convertToEntityAttribute(String dbData) {
        return EnumSet.allOf(EventStatus.class).stream()
                      .filter(e -> e.name().equals(dbData))
                      .findAny()
                      .orElseThrow(NoSuchElementException::new);
    }
}
