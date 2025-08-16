package com.hhplus.commerce.spring.domain.product.mapper;

import com.hhplus.commerce.spring.old.domain.product.dto.ProductDeductInfo;
import com.hhplus.commerce.spring.old.domain.product.dto.ProductInfo;
import com.hhplus.commerce.spring.old.domain.product.dto.ProductInfoPaged;
import com.hhplus.commerce.spring.old.domain.product.mapper.ProductInfoMapper;
import com.hhplus.commerce.spring.old.domain.product.model.Product;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-13T21:03:19+0900",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 18.0.2 (Amazon.com Inc.)"
)
@Component
public class ProductInfoMapperImpl implements ProductInfoMapper {

    @Override
    public ProductInfo toProductInfo(Product product) {
        if ( product == null ) {
            return null;
        }

        Integer stockCount = null;
        Long id = null;
        String name = null;
        String desc = null;
        Integer price = null;

        stockCount = product.getCount();
        id = product.getId();
        name = product.getName();
        desc = product.getDesc();
        price = product.getPrice();

        LocalDateTime regDate = null;
        LocalDateTime modDate = null;

        ProductInfo productInfo = new ProductInfo( id, name, desc, price, stockCount, regDate, modDate );

        return productInfo;
    }

    @Override
    public ProductInfoPaged toProductInfoPage(int totalCount, int currentPage, List<ProductInfo> productInfoList) {
        if ( productInfoList == null ) {
            return null;
        }

        ProductInfoPaged.ProductInfoPagedBuilder productInfoPaged = ProductInfoPaged.builder();

        productInfoPaged.totalCount( totalCount );
        productInfoPaged.currentPage( currentPage );
        List<ProductInfo> list = productInfoList;
        if ( list != null ) {
            productInfoPaged.productInfoList( new ArrayList<ProductInfo>( list ) );
        }

        return productInfoPaged.build();
    }

    @Override
    public ProductDeductInfo toProductDeductInfo(int totalAmount, Map<Long, Product> productMap) {
        if ( productMap == null ) {
            return null;
        }

        Integer totalAmount1 = null;
        totalAmount1 = totalAmount;
        List<ProductInfo> productInfos = null;
        productInfos = mapToProductInfoList( productMap );

        ProductDeductInfo productDeductInfo = new ProductDeductInfo( totalAmount1, productInfos );

        return productDeductInfo;
    }
}
