package io.dodn.springboot.storage.db.core.entity.product.response;

import io.dodn.springboot.core.enums.ProductType.ProductSellingStatus;
import io.dodn.springboot.core.enums.ProductType.ProductType;
import io.dodn.springboot.storage.db.core.entity.product.ProductEntity;

public record CreateProductPersistenceResponse(Long productNumber, ProductType type, ProductSellingStatus sellingStatus,
        String name, Long price, Long stockQuantity) {

    public static CreateProductPersistenceResponse of(ProductEntity productEntity) {
        return new CreateProductPersistenceResponse(productEntity.getProductNumber(), productEntity.getType(),
                productEntity.getSellingStatus(), productEntity.getName(), productEntity.getPrice(),
                productEntity.getStockQuantity());
    }

}
