package io.dodn.springboot.storage.db.core.entity.orderproduct;

import org.springframework.data.jpa.repository.JpaRepository;

interface OrderProductJPARepository extends JpaRepository<OrderProductEntity, Long> {

}
