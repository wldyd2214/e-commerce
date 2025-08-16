package com.hhplus.commerce.spring.old.domain.product.mapper;

import com.hhplus.commerce.spring.old.domain.product.dto.ProductDeductInfo;
import com.hhplus.commerce.spring.old.domain.product.dto.ProductInfo;
import com.hhplus.commerce.spring.old.domain.product.dto.ProductInfoPaged;
import com.hhplus.commerce.spring.old.domain.product.model.Product;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface ProductInfoMapper {

    default List<ProductInfo> toProductInfoList(List<Product> products) {

        if (products == null) {
            return List.of();
        }

        return products.stream()
                       .map(this::toProductInfo)
                       .toList();
    }

    @Mapping(source = "count", target = "stockCount")
    ProductInfo toProductInfo(Product product);

    ProductInfoPaged toProductInfoPage(int totalCount, int currentPage, List<ProductInfo> productInfoList);

    @Mapping(target = "productInfos", source = "productMap", qualifiedByName = "mapToProductInfoList")
    ProductDeductInfo toProductDeductInfo(int totalAmount, Map<Long, Product> productMap);

    @Named("mapToProductInfoList")
    default List<ProductInfo> mapToProductInfoList(Map<Long, Product> productMap) {
        return productMap.values().stream()
            .map(this::toProductInfo)
            .collect(Collectors.toList());
    }
}
