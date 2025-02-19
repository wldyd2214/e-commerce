package com.hhplus.commerce.spring.presentation.product.mapper;

import com.hhplus.commerce.spring.domain.product.dto.ProductInfo;
import com.hhplus.commerce.spring.domain.product.dto.ProductInfoList;
import com.hhplus.commerce.spring.presentation.product.dto.ProductDTO;
import com.hhplus.commerce.spring.presentation.product.dto.response.ProductListResponse;
import java.util.stream.Collectors;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProductResponseMapper {

    ProductResponseMapper INSTANCE = Mappers.getMapper(ProductResponseMapper.class);

    default ProductListResponse toProductResponse(ProductInfoList productInfoList) {

        List<ProductDTO> productDTOList = productInfoList.getProductInfoList()
                                                         .stream()
                                                         .map(this::toProductDTO)
                                                         .collect(Collectors.toList());

        return ProductListResponse.builder()
                                  .totalCount(productInfoList.getTotalCount())
                                  .currentPage(productInfoList.getCurrentPage())
                                  .products(productDTOList)
                                  .build();
    }

    @Mapping(source = "price", target = "consumerPrice")
    ProductDTO toProductDTO(ProductInfo productInfo);
}
