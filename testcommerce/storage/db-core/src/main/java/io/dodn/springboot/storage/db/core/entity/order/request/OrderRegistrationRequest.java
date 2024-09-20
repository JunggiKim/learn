package io.dodn.springboot.storage.db.core.entity.order.request;

import io.dodn.springboot.core.enums.OrderStatus;
import io.dodn.springboot.storage.db.core.entity.order.OrderEntity;

import java.time.LocalDateTime;
import java.util.List;

public record OrderRegistrationRequest(OrderStatus orderStatus, Long totalPrice, LocalDateTime registeredDateTime,
        String userEmail) {

    public OrderEntity toEntity() {
        return OrderEntity.create(orderStatus, totalPrice, registeredDateTime, userEmail);

    }

}
