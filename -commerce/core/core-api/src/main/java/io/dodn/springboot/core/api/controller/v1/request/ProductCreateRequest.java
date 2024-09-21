package io.dodn.springboot.core.api.controller.v1.request;

import io.dodn.springboot.core.domain.product.request.ProductCreateServiceRequest;
import io.dodn.springboot.core.enums.ProductType.ProductSellingStatus;
import io.dodn.springboot.core.enums.ProductType.ProductType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Builder;
import lombok.Getter;

public record ProductCreateRequest(@NotNull(message = "상품 타입은 필수입니다.") ProductType type,
        @NotNull(message = "상품 판매상태는 필수입니다.") ProductSellingStatus sellingStatus,
        @NotBlank(message = "상품 이름은 필수입니다.") String name, @Positive(message = "상품 가격은 양수여야 합니다.") Long price,
        @PositiveOrZero(message = "상품 수량은 0개 이상만 가능합니다.") Long stockQuantity) {
    public ProductCreateServiceRequest toServiceRequest() {
        return new ProductCreateServiceRequest(this.type, this.sellingStatus, this.name, this.price,
                this.stockQuantity);
    }
}
