package io.dodn.springboot.storage.db.core.entity.product.request;

import io.dodn.springboot.core.enums.ProductType.ProductSellingStatus;
import io.dodn.springboot.core.enums.ProductType.ProductType;
import io.dodn.springboot.storage.db.core.entity.product.ProductEntity;

public record CreateProductPersistenceRequest(ProductType type, ProductSellingStatus sellingStatus, String name,
        Long price, Long stockQuantity, Long nextProductNumber) {

    public ProductEntity toEntity() {
        return ProductEntity.builder()
            .productNumber(nextProductNumber)
            .type(type)
            .sellingStatus(sellingStatus)
            .name(name)
            .price(price)
            .stockQuantity(stockQuantity)
            .build();
    };

}
