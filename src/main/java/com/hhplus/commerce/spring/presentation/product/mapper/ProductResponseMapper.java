package com.hhplus.commerce.spring.presentation.product.mapper;

import com.hhplus.commerce.spring.domain.product.dto.ProductInfo;
import com.hhplus.commerce.spring.domain.product.dto.ProductInfoPage;
import com.hhplus.commerce.spring.presentation.product.response.ProductsResponse;
import com.hhplus.commerce.spring.presentation.product.dto.ProductDTO;
import com.hhplus.commerce.spring.presentation.product.dto.response.ProductListResponse;
import java.util.stream.Collectors;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductResponseMapper {

    default ProductsResponse toProductsResponse(List<ProductInfo> productInfoList) {
        return ProductsResponse.builder()
                               .products(toProductDTOList(productInfoList))
                               .build();
    }

    default ProductListResponse toProductListResponse(ProductInfoPage productInfoPage) {

        int totalPageCount = productInfoPage.getTotalCount();
        int currentPage = productInfoPage.getCurrentPage();
        List<ProductDTO> productDTOList = productInfoPage.getProductInfoList()
                                                         .stream()
                                                         .map(this::toProductDTO)
                                                         .collect(Collectors.toList());

        return createProductListResponse(totalPageCount, currentPage, productDTOList);
    }

    @Mapping(source = "price", target = "consumerPrice")
    ProductDTO toProductDTO(ProductInfo productInfo);

    List<ProductDTO> toProductDTOList(List<ProductInfo> productInfoList);

    ProductListResponse createProductListResponse(int totalCount, int currentPage, List<ProductDTO> products);
}
