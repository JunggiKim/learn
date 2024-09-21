package io.dodn.springboot.storage.db.core.entity.order;

import io.dodn.springboot.core.enums.OrderStatus;
import io.dodn.springboot.storage.db.core.entity.order.request.OrderRegistrationRequest;
import io.dodn.springboot.storage.db.core.entity.order.response.OrderRegistrationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryImpl implements OrderRepository {

    private final OrderJPARepository orderJPARepository;




    @Override
    public List<OrderEntity> findOrdersBy(LocalDateTime startDateTime, LocalDateTime endDateTime,
            OrderStatus orderStatus) {
        return orderJPARepository.findOrdersBy(startDateTime, endDateTime, orderStatus);
    }

    @Override
    public OrderRegistrationResponse orderRegistration(OrderRegistrationRequest request) {
        return OrderRegistrationResponse.of(orderJPARepository.save(request.toEntity()));
    }

}
