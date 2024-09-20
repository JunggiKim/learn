package io.dodn.springboot.core.domain.order.request;

import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

@Builder
public record OrderCreateServiceRequest(
		String userEmail,
        @NotEmpty(message = "주문 상품 목록은 필수입니다.")
		List<productDTO> productDTOS) {

    public record productDTO(Long productId, Long quantity

    ) {

    }

    // order에서 주문생성 로직과 DTO 변환 고민 중...
    // 애초에 상품에 맞게 데이터를 api에서 받아야 한다.
    //
    // public List<Product> toProductList(LocalDateTime registeredDateTime) {
    //
    // this.orderCreateServiceRequestProductDTOS.stream()
    // .map(dto -> {
    // Product.builder()
    // .productNumber(dto.productNumber)
    // .type(Pro)
    // })
    //
    //
    // return Product.builder()
    // .productNumber()
    //
    // .build();
    // }
    //
    // public Product toProduct2(LocalDateTime registeredDateTime) {
    //
    //
    // return Product.builder()
    // .productNumber(this.orderCreateServiceRequestProductDTOS.)
    //
    // .build();
    // }
    //
    //
    //
    // public Order toOrder() {
    // return Order.builder()
    // .
    // }

}
