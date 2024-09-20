package io.dodn.springboot.core.domain.order.response;

import java.time.LocalDateTime;
import java.util.List;

import io.dodn.springboot.core.domain.product.response.CreateProductResponse;
import io.dodn.springboot.core.enums.ProductType.ProductType;
import io.dodn.springboot.storage.db.core.entity.order.response.OrderRegistrationResponse;
import io.dodn.springboot.storage.db.core.entity.orderproduct.response.OrderProductRegistrationResponse;
import lombok.Builder;

public record CreateOrderResponse(
        Long orderId,
        Long totalPrice,
        LocalDateTime registeredDateTime,
        List<CreateOrderResponse.ProductDto> products,
        String userEmail

) {
    @Builder
    record ProductDto(Long productNumber,
                      ProductType type,
                      String name,
                      Long price) {
        public static CreateOrderResponse.ProductDto of(OrderProductRegistrationResponse response) {
            return CreateOrderResponse.ProductDto.builder()
                    .productNumber(response.productNumber())
                    .type(response.type())
                    .name(response.name())
                    .price(response.price())
                    .build();
        }

    }


    public static CreateOrderResponse of(OrderRegistrationResponse orderResponse,
                                         List<OrderProductRegistrationResponse> orderProducts) {
        return new CreateOrderResponse(
                orderResponse.orderId(),
                orderResponse.totalPrice(),
                orderResponse.registeredDateTime(),
                orderProducts.stream().map(CreateOrderResponse.ProductDto::of).toList(),
                orderResponse.userEmail());
    }
}
