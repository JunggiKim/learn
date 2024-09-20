package io.dodn.springboot.storage.db.core.entity.order.request;

import io.dodn.springboot.core.enums.ProductType.ProductType;

import java.math.BigDecimal;

public record OrderRegistrationRequestProductDto(Long productId, ProductType type, String name, BigDecimal price) {

}
