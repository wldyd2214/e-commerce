package com.hhplus.commerce.spring.presentation.product.mapper;

import com.hhplus.commerce.spring.old.domain.product.dto.ProductQuery;
import com.hhplus.commerce.spring.old.presentation.product.dto.request.ProductListRequest;
import com.hhplus.commerce.spring.old.presentation.product.mapper.ProductRequestMapper;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-13T21:03:19+0900",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 18.0.2 (Amazon.com Inc.)"
)
@Component
public class ProductRequestMapperImpl implements ProductRequestMapper {

    @Override
    public ProductQuery.Paged toProductQueryList(ProductListRequest request) {
        if ( request == null ) {
            return null;
        }

        Integer count = null;
        Integer page = null;
        String name = null;

        count = request.getCount();
        page = request.getPage();
        name = request.getName();

        ProductQuery.Paged paged = new ProductQuery.Paged( count, page, name );

        return paged;
    }
}
