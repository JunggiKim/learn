package io.dodn.springboot.core.domain.order;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import io.dodn.springboot.core.domain.orderproduct.OrderProduct;
import io.dodn.springboot.core.enums.OrderStatus;
import lombok.Builder;
@Builder
public record Order (
     Long orderId,
     OrderStatus orderStatus,
     BigDecimal totalPrice,
     LocalDateTime registeredDateTime,
     String userEmail,
     List<OrderProduct> orderProducts
){

    public static Order create(List<OrderProduct> products, LocalDateTime registeredDateTime, String userEmail) {
        return Order.builder()
            .orderStatus(OrderStatus.INIT)
            .totalPrice(calculateTotalPrice(products))
            .orderProducts(products)
            .registeredDateTime(registeredDateTime)
            .userEmail(userEmail)
            .build();
    }

    public static BigDecimal calculateTotalPrice(List<OrderProduct> products) {
        return BigDecimal.valueOf(
            products.stream()
                .mapToLong(value -> value.price().longValue())
                .sum());
    }

}
