package io.dodn.springboot.storage.db.core.entity.order.response;

import io.dodn.springboot.core.enums.OrderStatus;
import io.dodn.springboot.storage.db.core.entity.order.OrderEntity;

import java.time.LocalDateTime;

public record OrderRegistrationResponse(Long orderId, OrderStatus orderStatus, Long totalPrice,
        LocalDateTime registeredDateTime, String userEmail) {

    public static OrderRegistrationResponse of(OrderEntity entity) {
        return new OrderRegistrationResponse(entity.getId(), entity.getOrderStatus(), entity.getTotalPrice(),
                entity.getRegisteredDateTime(), entity.getUserEmail());

    }

}
