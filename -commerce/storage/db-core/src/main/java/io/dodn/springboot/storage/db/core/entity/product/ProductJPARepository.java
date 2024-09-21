package io.dodn.springboot.storage.db.core.entity.product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import io.dodn.springboot.core.enums.ProductType.ProductSellingStatus;

@Repository
public interface ProductJPARepository extends JpaRepository<ProductEntity, Long> {

    /**
     * select * from product where selling_Status in('SELLING','HOLD')
     **/
    List<ProductEntity> findAllBySellingStatusIn(List<ProductSellingStatus> sellingStatuses);

    List<ProductEntity> findAllByProductNumberIn(List<Long> productNumbers);

    @Query(value = "select p.product_number from Product p order by id desc limit 1", nativeQuery = true)
    Long findLatesProductNumber();

}
