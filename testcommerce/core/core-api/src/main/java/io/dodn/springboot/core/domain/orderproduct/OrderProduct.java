package io.dodn.springboot.core.domain.orderproduct;

import java.math.BigDecimal;

import io.dodn.springboot.core.enums.ProductType.ProductType;
import lombok.Builder;
@Builder
public record OrderProduct (
     Long id,
     Long orderId,
     Long productId,
     ProductType type,
     String name,
     BigDecimal price
) {

    public static OrderProduct of( Long productId, ProductType type, String name,
            BigDecimal price) {
        return OrderProduct.builder()
            .productId(productId)
            .type(type)
            .name(name)
            .price(price)
            .build();
    }

}
