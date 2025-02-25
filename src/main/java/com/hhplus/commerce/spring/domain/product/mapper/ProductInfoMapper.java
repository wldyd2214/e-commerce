package com.hhplus.commerce.spring.domain.product.mapper;

import com.hhplus.commerce.spring.domain.product.dto.ProductDeductInfo;
import com.hhplus.commerce.spring.domain.product.dto.ProductInfo;
import com.hhplus.commerce.spring.domain.product.dto.ProductInfoPage;
import com.hhplus.commerce.spring.domain.product.dto.request.ProductCommand.DeductProduct;
import com.hhplus.commerce.spring.domain.product.entity.Product;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

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

    @Mapping(target = "productInfos", source = "productMap", qualifiedByName = "mapToProductInfoList")
    ProductDeductInfo toProductDeductInfo(int totalAmount, Map<Long, Product> productMap);

    @Named("mapToProductInfoList")
    default List<ProductInfo> mapToProductInfoList(Map<Long, Product> productMap) {
        return productMap.values().stream()
            .map(this::toProductInfo)
            .collect(Collectors.toList());
    }
}
