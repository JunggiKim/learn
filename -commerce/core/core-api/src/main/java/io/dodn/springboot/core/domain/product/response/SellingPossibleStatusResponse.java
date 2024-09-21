package io.dodn.springboot.core.domain.product.response;

import io.dodn.springboot.core.enums.ProductType.ProductType;
import io.dodn.springboot.storage.db.core.entity.product.response.SellingPossibleStatusPersistenceResponse;
import lombok.Builder;

@Builder
public record SellingPossibleStatusResponse(Long productNumber, ProductType type, String name, Long price,
        Long stockQuantity) {

    public static SellingPossibleStatusResponse of(SellingPossibleStatusPersistenceResponse response) {
        return SellingPossibleStatusResponse.builder()
            .productNumber(response.productNumber())
            .type(response.type())
            .name(response.name())
            .price(response.price())
            .stockQuantity(response.stockQuantity())
            .build();

    }

}
