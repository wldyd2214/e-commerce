package com.hhplus.commerce.spring.old.presentation.product.mapper;

import com.hhplus.commerce.spring.old.domain.product.dto.ProductQuery.Paged;
import com.hhplus.commerce.spring.old.presentation.product.dto.request.ProductListRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel="spring")
public interface ProductRequestMapper {

    Paged toProductQueryList(ProductListRequest request);
}
