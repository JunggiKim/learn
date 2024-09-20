package io.dodn.springboot.core.domain.order.response;

import io.dodn.springboot.core.enums.ProductType.ProductSellingStatus;
import io.dodn.springboot.core.enums.ProductType.ProductType;
import io.dodn.springboot.storage.db.core.entity.orderproduct.response.OrderProductRegistrationResponse;
import lombok.Builder;

@Builder
public record CreateOrderProductResponse(Long productNumber, ProductType type, String name, Long price) {

    public static CreateOrderProductResponse of(OrderProductRegistrationResponse response) {
        return CreateOrderProductResponse.builder()
            .productNumber(response.productNumber())
            .type(response.type())
            .name(response.name())
            .price(response.price())
            .build();
    }
}
