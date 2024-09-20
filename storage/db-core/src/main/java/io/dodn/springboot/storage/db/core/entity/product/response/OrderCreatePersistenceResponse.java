package io.dodn.springboot.storage.db.core.entity.product.response;

import io.dodn.springboot.core.enums.ProductType.ProductSellingStatus;
import io.dodn.springboot.core.enums.ProductType.ProductType;
import io.dodn.springboot.storage.db.core.entity.product.ProductEntity;

public record OrderCreatePersistenceResponse(Long productId, ProductType type, String name, Long price) {

    public static OrderCreatePersistenceResponse of(ProductEntity productEntity) {
        return new OrderCreatePersistenceResponse(productEntity.getId(), productEntity.getType(),
                productEntity.getName(), productEntity.getPrice());
    }

}
