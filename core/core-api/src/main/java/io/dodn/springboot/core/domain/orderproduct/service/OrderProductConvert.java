package io.dodn.springboot.core.domain.orderproduct.service;

import java.math.BigDecimal;
import java.util.List;

import io.dodn.springboot.storage.db.core.entity.orderproduct.request.OrderProductRegistrationRequest;
import org.springframework.stereotype.Component;

import io.dodn.springboot.core.domain.orderproduct.OrderProduct;
import io.dodn.springboot.storage.db.core.entity.product.response.OrderCreatePersistenceResponse;

@Component
public class OrderProductConvert {

    public static OrderProduct toDomain(OrderCreatePersistenceResponse dto) {
        return OrderProduct.of(dto.productId(), dto.type(), dto.name(), BigDecimal.valueOf(dto.price()));
    }

    public List<OrderProductRegistrationRequest> toOrderProductRegistrationRequest(List<OrderProduct> orderProductList,
            Long orderId) {
        return orderProductList.stream()
            .map(orderProduct -> new OrderProductRegistrationRequest(orderProduct.productId(),
                    orderProduct.type(), orderProduct.name(),
                    Long.parseLong(String.valueOf(orderProduct.price())), orderId))
            .toList();

    }

    //
    // public static OrderProduct toDomain2(OrderCreatePersistenceResponse dto) {
    // Product product = Product.builder()
    // .productNumber(dto.productNumber())
    // .type(dto.type())
    // .sellingStatus(dto.sellingStatus())
    // .name(dto.name())
    // .price(BigDecimal.valueOf(dto.price()))
    // .build();
    //
    // return OrderProduct.builder()
    // .product(product)
    // .build();
    //
    // }

}
