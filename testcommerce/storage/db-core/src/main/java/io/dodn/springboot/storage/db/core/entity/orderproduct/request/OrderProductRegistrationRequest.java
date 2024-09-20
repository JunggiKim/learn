package io.dodn.springboot.storage.db.core.entity.orderproduct.request;

import io.dodn.springboot.core.enums.ProductType.ProductType;
import io.dodn.springboot.storage.db.core.entity.orderproduct.OrderProductEntity;

public record OrderProductRegistrationRequest(Long productId, ProductType type, String name, Long price, Long orderId) {

    public OrderProductEntity toEntity() {
        return OrderProductEntity.of(this.productId, this.type, this.name, this.price, this.orderId);

    }
}
