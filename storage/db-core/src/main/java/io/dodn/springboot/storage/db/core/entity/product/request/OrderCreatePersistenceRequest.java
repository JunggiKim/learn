package io.dodn.springboot.storage.db.core.entity.product.request;

import java.math.BigDecimal;

import io.dodn.springboot.core.enums.ProductType.ProductSellingStatus;
import io.dodn.springboot.core.enums.ProductType.ProductType;
import io.dodn.springboot.storage.db.core.entity.product.ProductEntity;

public record OrderCreatePersistenceRequest(Long productId, ProductType type, ProductSellingStatus sellingStatus,
        String name, BigDecimal price, Long stockQuantity) {
    public ProductEntity toEntity() {
        return ProductEntity.builder()
            .id(productId)
            .type(type)
            .sellingStatus(sellingStatus)
            .name(name)
            .price(Long.valueOf(String.valueOf(price)))
            .stockQuantity(stockQuantity)
            .build();
    }

}
