package io.dodn.springboot.storage.db.core.entity.orderproduct;

import io.dodn.springboot.storage.db.core.entity.orderproduct.request.OrderProductRegistrationRequest;
import io.dodn.springboot.storage.db.core.entity.orderproduct.response.OrderProductRegistrationResponse;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
class OrderProductRepositoryImpl implements OrderProductRepository {

    private final OrderProductJPARepository orderProductJPARepository;

    @Override
    public List<OrderProductRegistrationResponse> orderProductRegistration(
            List<OrderProductRegistrationRequest> requests) {
        List<OrderProductEntity> orderProductList = requests.stream()
            .map(OrderProductRegistrationRequest::toEntity)
            .toList();

        return orderProductJPARepository.saveAll(orderProductList)
            .stream()
            .map(OrderProductRegistrationResponse::of)
            .toList();

    }

}
