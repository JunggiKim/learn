package io.dodn.springboot.core.domain.order.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Component;

import io.dodn.springboot.core.api.support.Business;
import io.dodn.springboot.core.domain.order.Order;
import io.dodn.springboot.core.domain.order.request.OrderCreateServiceRequest;
import io.dodn.springboot.core.domain.order.response.CreateOrderResponse;
import io.dodn.springboot.core.domain.orderproduct.OrderProduct;
import io.dodn.springboot.core.domain.orderproduct.service.OrderProductConvert;
import io.dodn.springboot.core.domain.product.service.ProductBusiness;
import io.dodn.springboot.storage.db.core.entity.order.OrderRepository;
import io.dodn.springboot.storage.db.core.entity.order.response.OrderRegistrationResponse;
import io.dodn.springboot.storage.db.core.entity.orderproduct.OrderProductRepository;
import io.dodn.springboot.storage.db.core.entity.orderproduct.response.OrderProductRegistrationResponse;
import io.dodn.springboot.storage.db.core.entity.product.ProductRepository;
import io.dodn.springboot.storage.db.core.entity.product.response.OrderCreatePersistenceResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class OrderBusiness {

    private final OrderRepository orderRepository;

    private final OrderProductRepository orderProductRepository;

    private final ProductBusiness productBusiness;

    private final OrderProductConvert orderProductConvert;

    private final OrderConvert orderConvert;

    public List<OrderProduct> orderProductDeductQuantities(List<OrderCreateServiceRequest.productDTO> requests)
            throws IllegalAccessException {


        return productBusiness.productDeductQuantities(requests).stream()
                .map(OrderProductConvert::toDomain).toList();
    }

    public CreateOrderResponse orderRegistration(List<OrderProduct> orderProductList, String userEmail,
            LocalDateTime registeredDateTime) {

        Order order = Order.create(orderProductList, registeredDateTime, userEmail);

        OrderRegistrationResponse orderResponse = orderRepository
            .orderRegistration(orderConvert.toOrderRegistrationRequest(order));

        List<OrderProductRegistrationResponse> responseList = orderProductRepository
            .orderProductRegistration(orderProductConvert.toOrderProductRegistrationRequest(order.orderProducts(),
                    orderResponse.orderId()));

        return CreateOrderResponse.of(orderResponse, responseList);
    }

}
