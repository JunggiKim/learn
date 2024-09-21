package io.dodn.springboot.storage.db.core.entity.order;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import io.dodn.springboot.core.enums.OrderStatus;

@Repository
public interface OrderJPARepository extends JpaRepository<OrderEntity, Long> {

    @Query("select o from OrderEntity o where o.registeredDateTime >= :startDateTime and o.registeredDateTime < :endDateTime and o.orderStatus = :orderStatus")
    List<OrderEntity> findOrdersBy(LocalDateTime startDateTime, LocalDateTime endDateTime, OrderStatus orderStatus);

}
