package io.dodn.springboot.storage.db.core.entity.order;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import io.dodn.springboot.core.enums.OrderStatus;
import io.dodn.springboot.storage.db.core.BaseEntity;
import io.dodn.springboot.storage.db.core.entity.order.request.OrderRegistrationRequestProductDto;
import io.dodn.springboot.storage.db.core.entity.orderproduct.OrderProductEntity;
import io.dodn.springboot.storage.db.core.entity.product.ProductEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "orders")
public class OrderEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    private Long totalPrice;

    private LocalDateTime registeredDateTime;

    private String userEmail;

    @Builder
    private OrderEntity(String userEmail, Long totalPrice, OrderStatus orderStatus, LocalDateTime registeredDateTime) {
        this.orderStatus = orderStatus;
        this.totalPrice = totalPrice;
        this.registeredDateTime = registeredDateTime;
        this.userEmail = userEmail;
    }

    public static OrderEntity create(OrderStatus orderStatus, Long totalPrice, LocalDateTime registeredDateTime,
            String userEmail) {
        return OrderEntity.builder()
            .orderStatus(orderStatus)
            .totalPrice(totalPrice)
            .registeredDateTime(registeredDateTime)
            .userEmail(userEmail)
            .build();
    }

}
