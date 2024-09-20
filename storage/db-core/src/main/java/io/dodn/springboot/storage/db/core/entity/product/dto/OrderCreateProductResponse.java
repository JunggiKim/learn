package io.dodn.springboot.storage.db.core.entity.product.dto;

import io.dodn.springboot.core.enums.ProductType.ProductSellingStatus;
import io.dodn.springboot.core.enums.ProductType.ProductType;
import io.dodn.springboot.storage.db.core.entity.product.ProductEntity;

import java.util.List;

public record OrderCreateProductResponse(Long productId, ProductType type, ProductSellingStatus sellingStatus,
        String name, Long price, Long quantity) {

    private static OrderCreateProductResponse of(ProductEntity productEntity) {
        return new OrderCreateProductResponse(productEntity.getId(), productEntity.getType(),
                productEntity.getSellingStatus(), productEntity.getName(), productEntity.getPrice(),
                productEntity.getStockQuantity());
    }

    public static List<OrderCreateProductResponse> toList(List<ProductEntity> ProductEntitys) {
        return ProductEntitys.stream().map(OrderCreateProductResponse::of).toList();
    }
}
