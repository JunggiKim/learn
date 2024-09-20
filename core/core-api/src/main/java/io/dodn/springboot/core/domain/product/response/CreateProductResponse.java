package io.dodn.springboot.core.domain.product.response;

import io.dodn.springboot.core.enums.ProductType.ProductSellingStatus;
import io.dodn.springboot.core.enums.ProductType.ProductType;
import io.dodn.springboot.storage.db.core.entity.product.response.CreateProductPersistenceResponse;
import lombok.Builder;

@Builder
public record CreateProductResponse(Long productNumber, ProductType type, ProductSellingStatus sellingStatus,
        String name, Long price, Long stockQuantity) {

    public static CreateProductResponse of(CreateProductPersistenceResponse response) {
        return CreateProductResponse.builder()
            .productNumber(response.productNumber())
            .type(response.type())
            .sellingStatus(response.sellingStatus())
            .name(response.name())
            .price(response.price())
            .stockQuantity(response.stockQuantity())
            .build();
    }
}
