package io.dodn.springboot.storage.db.core.entity.orderproduct.response;

import io.dodn.springboot.core.enums.ProductType.ProductType;
import io.dodn.springboot.storage.db.core.entity.orderproduct.OrderProductEntity;

public record OrderProductRegistrationResponse(Long productNumber, ProductType type, String name, Long price) {

    public static OrderProductRegistrationResponse of(OrderProductEntity entity) {
        return new OrderProductRegistrationResponse(entity.getProductNumber(), entity.getType(), entity.getName(),
                entity.getPrice());

    }

}
