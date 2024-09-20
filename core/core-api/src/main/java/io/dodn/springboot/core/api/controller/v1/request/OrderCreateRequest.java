package io.dodn.springboot.core.api.controller.v1.request;

import java.util.List;

import io.dodn.springboot.core.domain.order.request.OrderCreateServiceRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public record OrderCreateRequest(@NotBlank(message = "주문한 사용자의 이메일은 필수 입니다.") String userEmail,
        @NotEmpty(message = "주문 상품은 필수입니다.") List<ProductDTO> productDTOS) {

    public record ProductDTO(Long productNumber, Long quantity) {
    }

    public OrderCreateServiceRequest toServiceRequest() {
        return new OrderCreateServiceRequest(this.userEmail,
                this.productDTOS.stream()
                    .map(orderCreateRequestProductDTO -> new OrderCreateServiceRequest.productDTO(
                            orderCreateRequestProductDTO.productNumber, orderCreateRequestProductDTO.quantity))
                    .toList());

    }

    // 생각을 해봐야하는건 어차피 프러덕트의 넘버만 보내나 모든 정보를 값객체에담아서보내거나
    // 둘다 디비를 조회해야하는건가? 그런건가 싶으면 다시 그냥 기존 오더의 비즈니스 로직을 따르자
    // 생각을 해보면 괜히 배민과 캐치테이블인 이 커머스 도메인의 비즈니스로직을 본게 아니다
    // 생각을 다시 해보자

}
