package com.hhplus.commerce.spring.presentation.product.mapper;

import ch.qos.logback.core.model.ComponentModel;
import com.hhplus.commerce.spring.domain.product.dto.ProductQuery;
import com.hhplus.commerce.spring.presentation.product.dto.request.ProductListRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper(componentModel="spring")
public interface ProductRequestMapper {

    ProductQuery.Paged toProductQueryList(ProductListRequest request);
}
