package io.dodn.springboot.core.test.v1.domain.product;

import io.dodn.springboot.core.domain.product.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ProductTest {

    @DisplayName("재고의 수량이 제공된 수량보다 작은지 확인한다.")
    @Test
    void isQuantityLessThan() {
        // given
        Product product = Product.builder().stockQuantity(1L).build();
        Long stockQuantity = 2L;

        // when
        boolean result = product.isQuantityLessThan(stockQuantity);

        // then
        assertThat(result).isTrue();
    }

    @DisplayName("재고를 주어진 개수만큼 차감할 수 있다.")
    @Test
    void deductQuantity() throws IllegalAccessException {
        // given
        Product product = Product.builder().stockQuantity(1L).build();
        Long stockQuantity = 1L;

        // when
        product.deductQuantity(stockQuantity);

        // then
        assertThat(product.getStockQuantity()).isZero();
    }

    @DisplayName("재고 보다 많은 수의 수량으로 차감되는 경우 예외가 발생한다.")
    @Test
    void deductQuantity2() throws IllegalAccessException {
        // given
        Product product = Product.builder().name("김치찌개").stockQuantity(1L).build();
        Long stockQuantity = 2L;

        // then when
        assertThatThrownBy(() -> product.deductQuantity(stockQuantity)).isInstanceOf(IllegalArgumentException.class)
            .hasMessage(product.getName() + "의 차감 할 재고 수량이 없습니다.");
    }

}
