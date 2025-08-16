package com.hhplus.commerce.spring.presentation.product.mapper;

import com.hhplus.commerce.spring.old.domain.product.dto.ProductInfo;
import com.hhplus.commerce.spring.old.presentation.product.dto.ProductDTO;
import com.hhplus.commerce.spring.old.presentation.product.dto.response.ProductResponse;
import com.hhplus.commerce.spring.old.presentation.product.mapper.ProductResponseMapper;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-13T21:03:19+0900",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 18.0.2 (Amazon.com Inc.)"
)
@Component
public class ProductResponseMapperImpl implements ProductResponseMapper {

    @Override
    public ProductDTO toProductDTO(ProductInfo productInfo) {
        if ( productInfo == null ) {
            return null;
        }

        ProductDTO.ProductDTOBuilder productDTO = ProductDTO.builder();

        productDTO.consumerPrice( productInfo.getPrice() );
        productDTO.id( productInfo.getId() );
        productDTO.name( productInfo.getName() );
        productDTO.stockCount( productInfo.getStockCount() );

        return productDTO.build();
    }

    @Override
    public List<ProductDTO> toProductDTOList(List<ProductInfo> productInfoList) {
        if ( productInfoList == null ) {
            return null;
        }

        List<ProductDTO> list = new ArrayList<ProductDTO>( productInfoList.size() );
        for ( ProductInfo productInfo : productInfoList ) {
            list.add( toProductDTO( productInfo ) );
        }

        return list;
    }

    @Override
    public ProductResponse.PagedProduct createPagedProduct(int totalCount, int currentPage, List<ProductDTO> products) {
        if ( products == null ) {
            return null;
        }

        Integer totalCount1 = null;
        totalCount1 = totalCount;
        Integer currentPage1 = null;
        currentPage1 = currentPage;
        List<ProductDTO> products1 = null;
        List<ProductDTO> list = products;
        if ( list != null ) {
            products1 = new ArrayList<ProductDTO>( list );
        }

        ProductResponse.PagedProduct pagedProduct = new ProductResponse.PagedProduct( totalCount1, currentPage1, products1 );

        return pagedProduct;
    }
}
