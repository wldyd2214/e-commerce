package com.hhplus.commerce.spring.presentation.product.mapper;

import com.hhplus.commerce.spring.domain.product.dto.ProductInfo;
import com.hhplus.commerce.spring.domain.product.dto.ProductInfoPaged;
import com.hhplus.commerce.spring.presentation.product.dto.response.ProductResponse;
import com.hhplus.commerce.spring.presentation.product.dto.ProductDTO;
import java.util.stream.Collectors;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductResponseMapper {

    default ProductResponse.PopularProduct toPopularProduct(List<ProductInfo> productInfoList) {
        return new ProductResponse.PopularProduct(toProductDTOList(productInfoList));
    }

    default ProductResponse.PagedProduct toPagedProduct(ProductInfoPaged productInfoPaged) {

        int totalPageCount = productInfoPaged.getTotalCount();
        int currentPage = productInfoPaged.getCurrentPage();
        List<ProductDTO> productDTOList = productInfoPaged.getProductInfoList()
            .stream()
            .map(this::toProductDTO)
            .collect(Collectors.toList());

        return createPagedProduct(totalPageCount, currentPage, productDTOList);
    }

    @Mapping(source = "price", target = "consumerPrice")
    ProductDTO toProductDTO(ProductInfo productInfo);

    List<ProductDTO> toProductDTOList(List<ProductInfo> productInfoList);

    ProductResponse.PagedProduct createPagedProduct(int totalCount, int currentPage, List<ProductDTO> products);
}
