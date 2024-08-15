//package com.hhplus.commerce.spring.api.order.model.convert;
//
//import com.hhplus.commerce.spring.api.order.service.request.OrderEvent;
//import jakarta.persistence.AttributeConverter;
//import org.springframework.stereotype.Component;
//
//import java.util.EnumSet;
//import java.util.NoSuchElementException;
//
//@Component
//public class PayloadConverter implements AttributeConverter<String, String> {
//
//    @Override
//    public String convertToDatabaseColumn(OrderEvent attribute) {
//        return attribute.;
//    }
//
//    @Override
//    public SupplierType convertToEntityAttribute(String dbData) {
//        return EnumSet.allOf(SupplierType.class).stream()
//                      .filter(e -> e.getCode().equals(dbData))
//                      .findAny()
//                      .orElseThrow(NoSuchElementException::new);
//    }
//}
