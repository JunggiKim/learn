package io.dodn.springboot.core.test.v1.persistence.product;

import io.dodn.springboot.core.enums.ProductType.ProductSellingStatus;
import io.dodn.springboot.core.enums.ProductType.ProductType;
import io.dodn.springboot.storage.db.core.entity.product.ProductEntity;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
record TestProductDto(Long productNumber, ProductType type, ProductSellingStatus sellingStatus, String name,
        BigDecimal price, Long stockQuantity) {

    public ProductEntity toEntity() {
        return ProductEntity.builder()
            .productNumber(productNumber)
            .type(type)
            .sellingStatus(sellingStatus)
            .name(name)
            .price(price.longValue())
            .stockQuantity(stockQuantity)
            .build();

    }

    public static TestProductDto of(ProductEntity productEntity) {
        return new TestProductDto(productEntity.getProductNumber(), productEntity.getType(),
                productEntity.getSellingStatus(), productEntity.getName(), BigDecimal.valueOf(productEntity.getPrice()),
                productEntity.getStockQuantity());
    }

}
