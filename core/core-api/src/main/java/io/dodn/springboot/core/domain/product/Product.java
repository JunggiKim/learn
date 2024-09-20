package io.dodn.springboot.core.domain.product;

import java.math.BigDecimal;

import io.dodn.springboot.core.enums.ProductType.ProductSellingStatus;
import io.dodn.springboot.core.enums.ProductType.ProductType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
public record Product (
     Long productId,
     Long productNumber,
     ProductType type,
     ProductSellingStatus sellingStatus,
     String name,
     BigDecimal price,
     Long stockQuantity
){


    public boolean isQuantityLessThan(Long quantity) {
        return this.stockQuantity < quantity;

    }

    public Product deductQuantity(Long quantity) throws IllegalAccessException {
        if (isQuantityLessThan(quantity)) {
            throw new IllegalArgumentException(this.name + "의 차감 할 재고 수량이 없습니다.");
        }
        long deductQuantity = stockQuantity - quantity;
        return Product.builder()
            .productId(this.productId)
            .productNumber(this.productNumber)
            .type(this.type)
            .sellingStatus(this.sellingStatus)
            .name(this.name)
            .price(this.price)
            .stockQuantity(deductQuantity)
            .build();
    }






}
