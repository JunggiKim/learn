package io.dodn.springboot.storage.db.core.entity.product.response;

import io.dodn.springboot.core.enums.ProductType.ProductType;
import io.dodn.springboot.storage.db.core.entity.product.ProductEntity;
import lombok.Builder;

@Builder
public record SellingPossibleStatusPersistenceResponse(
        Long productNumber,
        ProductType type,
        String name,
        Long price,
        Long stockQuantity) {

    public static SellingPossibleStatusPersistenceResponse of(ProductEntity productEntity) {
        return SellingPossibleStatusPersistenceResponse.builder()
            .productNumber(productEntity.getProductNumber())
            .type(productEntity.getType())
            .name(productEntity.getName())
            .price(productEntity.getPrice())
            .stockQuantity(productEntity.getStockQuantity())
            .build();

    }

}
