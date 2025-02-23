package com.hhplus.commerce.spring.domain.product.mapper;

import com.hhplus.commerce.spring.domain.product.dto.ProductInfo;
import com.hhplus.commerce.spring.domain.product.dto.ProductInfoPage;
import com.hhplus.commerce.spring.domain.product.entity.Product;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductInfoMapper {

    default List<ProductInfo> toProductInfoList(List<Product> products) {
        return products.stream()
                       .map(this::toProductInfo)
                       .toList();
    }

    @Mapping(source = "count", target = "stockCount")
    ProductInfo toProductInfo(Product product);

    ProductInfoPage toProductInfoPage(int totalCount, int currentPage, List<ProductInfo> productInfoList);
}
