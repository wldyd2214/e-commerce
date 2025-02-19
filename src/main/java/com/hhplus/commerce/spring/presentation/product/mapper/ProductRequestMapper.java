package com.hhplus.commerce.spring.presentation.product.mapper;

import com.hhplus.commerce.spring.domain.product.dto.ProductQuery;
import com.hhplus.commerce.spring.presentation.product.dto.request.ProductListRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductRequestMapper {

    ProductRequestMapper INSTANCE = Mappers.getMapper(ProductRequestMapper.class);

    ProductQuery.List toProductQueryList(ProductListRequest request);
}
