package io.dodn.springboot.core.test.v1.domain.order;

import io.dodn.springboot.core.domain.order.Order;
import io.dodn.springboot.core.domain.orderproduct.OrderProduct;
import io.dodn.springboot.core.enums.OrderStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static io.dodn.springboot.core.enums.ProductType.ProductType.CLOTHES;
import static org.assertj.core.api.Assertions.assertThat;

class OrderTest {

    @DisplayName("상품 리스트 에서 주문의 총 금액을 계산 한다.")
    @Test
    void calculateTotalPrice() {
        // given
        List<OrderProduct> products = List.of(createOrderProduct(1L, BigDecimal.valueOf(1000)),
                createOrderProduct(2L, BigDecimal.valueOf(2000)));
        // when
        Order order = Order.create(products, LocalDateTime.now(), "김정기");

        // then

        assertThat(order.getTotalPrice().longValue()).isEqualTo(3000L);

    }

    @DisplayName("주문 생성 시 주문 상태는 INIT이다.")
    @Test
    void init() {
        // given
        List<OrderProduct> products = List.of(createOrderProduct(1L, BigDecimal.valueOf(1000)),
                createOrderProduct(2L, BigDecimal.valueOf(1000)));
        String userEmail = "kjg424@naver.com";

        // when
        Order order = Order.create(products, LocalDateTime.now(), userEmail);

        // then
        assertThat(order.getOrderStatus()).isEqualByComparingTo(OrderStatus.INIT);

    }

    @DisplayName("주문 생성 시 주문 등록 시간을 기록한다..")
    @Test
    void registeredDateTime() {
        // given
        LocalDateTime registeredDateTime = LocalDateTime.now();
        String userEmail = "kjg424@naver.com";
        List<OrderProduct> products = List.of(createOrderProduct(1L, BigDecimal.valueOf(1000)),
                createOrderProduct(2L, BigDecimal.valueOf(1000)));

        // when
        Order order = Order.create(products, registeredDateTime, userEmail);

        // then

        assertThat(order.getRegisteredDateTime()).isEqualTo(registeredDateTime);

    }

    private OrderProduct createOrderProduct(Long productId, BigDecimal price) {
        return OrderProduct.builder().productId(productId).type(CLOTHES).name("메뉴이름").price(price).build();

    }

}