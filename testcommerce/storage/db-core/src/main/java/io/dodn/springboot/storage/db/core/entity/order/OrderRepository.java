package io.dodn.springboot.storage.db.core.entity.order;

import io.dodn.springboot.core.enums.OrderStatus;
import io.dodn.springboot.storage.db.core.entity.order.request.OrderRegistrationRequest;
import io.dodn.springboot.storage.db.core.entity.order.response.OrderRegistrationResponse;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepository {

    List<OrderEntity> findOrdersBy(LocalDateTime startDateTime, LocalDateTime endDateTime, OrderStatus orderStatus);

    OrderRegistrationResponse orderRegistration(OrderRegistrationRequest request);

}
