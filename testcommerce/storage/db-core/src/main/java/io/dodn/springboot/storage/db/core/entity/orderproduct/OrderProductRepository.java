package io.dodn.springboot.storage.db.core.entity.orderproduct;

import io.dodn.springboot.storage.db.core.entity.orderproduct.request.OrderProductRegistrationRequest;
import io.dodn.springboot.storage.db.core.entity.orderproduct.response.OrderProductRegistrationResponse;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface OrderProductRepository {

    List<OrderProductRegistrationResponse> orderProductRegistration(
            List<OrderProductRegistrationRequest> orderProductRegistrationRequest);

}
