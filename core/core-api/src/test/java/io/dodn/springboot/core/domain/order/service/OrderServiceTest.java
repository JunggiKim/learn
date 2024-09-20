package io.dodn.springboot.core.domain.order.service;

import io.dodn.springboot.core.domain.order.request.OrderCreateServiceRequest;
import io.dodn.springboot.core.domain.order.response.CreateOrderResponse;
import io.dodn.springboot.core.domain.product.service.ProductBusiness;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;


@ActiveProfiles("local")
@SpringBootTest
class OrderServiceTest {

    @Autowired
    private OrderService orderService;
    private OrderBusiness orderBusiness;
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;
    @Autowired
    private OrderConvert convert;


    @DisplayName("주문을 요청하면 성공적으로 주문 완료 한 값이 나온다")
    @Test
    void successCreateOrder() throws Exception {
        //given

        String email = "k42ke@naver.com";
        OrderCreateServiceRequest request = new OrderCreateServiceRequest(
                email,
                List.of(new OrderCreateServiceRequest.productDTO(
                        1L,
                        1L)
                )
        );
        LocalDateTime registeredDateTime = LocalDateTime.now();

        //when
        CreateOrderResponse order = orderService.createOrder(request, registeredDateTime);

        //then

        assertThat(order).extracting("registeredDateTime", "userEmail")
                .containsExactlyInAnyOrder(
                        registeredDateTime, email
                );

    }


}