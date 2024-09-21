package io.dodn.springboot.core.domain.order.service;

import java.time.LocalDateTime;
import java.util.List;

import io.dodn.springboot.core.domain.order.response.CreateOrderResponse;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.dodn.springboot.core.domain.order.request.OrderCreateServiceRequest;
import io.dodn.springboot.core.domain.orderproduct.OrderProduct;
import io.dodn.springboot.core.domain.product.service.ProductBusiness;
import lombok.RequiredArgsConstructor;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class OrderService {

    private final ProductBusiness productBusiness;

    private final OrderBusiness orderBusiness;

    private final ApplicationEventPublisher applicationEventPublisher;

    private final OrderConvert convert;

    // 주문이 들어오면 들어온 상품의 재고가 있는지 먼저 확인을 한 후
    // 재고 제거 후 주문을 등록하고 주문상품 등록 후
    // 성공한 주문내역을 반환 한다

    @Transactional
    public CreateOrderResponse createOrder(OrderCreateServiceRequest request, LocalDateTime registeredDateTime)
            throws IllegalAccessException {

       final List<OrderProduct> orderProductList = orderBusiness.orderProductDeductQuantities(request.productDTOS());

        return orderBusiness.orderRegistration(orderProductList, request.userEmail(), registeredDateTime);
    }

}
