package com.hhplus.commerce.spring.presentation.product.mapper;

import com.hhplus.commerce.spring.old.api.product.model.Product;
import com.hhplus.commerce.spring.presentation.product.ProductController;
import com.hhplus.commerce.spring.presentation.product.dto.ProductDTO;
import com.hhplus.commerce.spring.presentation.product.dto.ProductListResponse;
import java.util.stream.Collectors;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProductResponseMapper {

    ProductResponseMapper INSTANCE = Mappers.getMapper(ProductResponseMapper.class);

    default ProductListResponse toProductResponse(List<Product> product) {
        List<ProductDTO> productDTOList = product.stream()
                                                 .map(this::toProductDTO)
                                                 .collect(Collectors.toList());

        return ProductListResponse.builder()
                                  .products(productDTOList)
                                  .build();
    }

    @Mapping(source = "price", target = "consumerPrice")
    @Mapping(source = "count", target = "stockCount")
    ProductDTO toProductDTO(Product product);
}
