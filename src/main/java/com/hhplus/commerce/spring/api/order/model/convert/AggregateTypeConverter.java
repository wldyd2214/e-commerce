package com.hhplus.commerce.spring.api.order.model.convert;

import com.hhplus.commerce.spring.api.order.model.type.AggregateType;
import jakarta.persistence.AttributeConverter;

import java.util.EnumSet;
import java.util.NoSuchElementException;

public class AggregateTypeConverter implements AttributeConverter<AggregateType, String> {

    @Override
    public String convertToDatabaseColumn(AggregateType attribute) {
        return attribute.name();
    }

    @Override
    public AggregateType convertToEntityAttribute(String dbData) {
        return EnumSet.allOf(AggregateType.class).stream()
                      .filter(e -> e.name().equals(dbData))
                      .findAny()
                      .orElseThrow(NoSuchElementException::new);
    }
}
