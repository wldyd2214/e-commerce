package com.hhplus.commerce.spring.domain.product.mapper;

import com.hhplus.commerce.spring.domain.product.dto.ProductInfo;
import com.hhplus.commerce.spring.domain.product.entity.Product;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductInfoMapper {

    ProductInfoMapper INSTANCE = Mappers.getMapper(ProductInfoMapper.class);

    default List<ProductInfo> toProductInfoList(List<Product> products) {
        return products.stream()
                       .map(this::toProductInfo)
                       .toList();
    }

    @Mapping(source = "count", target = "stockCount")
    ProductInfo toProductInfo(Product product);
}
