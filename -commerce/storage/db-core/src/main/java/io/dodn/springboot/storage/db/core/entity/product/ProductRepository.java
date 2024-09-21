package io.dodn.springboot.storage.db.core.entity.product;

import java.util.List;

import org.springframework.stereotype.Repository;

import io.dodn.springboot.core.enums.ProductType.ProductSellingStatus;
import io.dodn.springboot.storage.db.core.entity.product.request.CreateProductPersistenceRequest;
import io.dodn.springboot.storage.db.core.entity.product.request.OrderCreatePersistenceRequest;
import io.dodn.springboot.storage.db.core.entity.product.response.AllFiledProductEntityDTO;
import io.dodn.springboot.storage.db.core.entity.product.response.CreateProductPersistenceResponse;
import io.dodn.springboot.storage.db.core.entity.product.response.OrderCreatePersistenceResponse;
import io.dodn.springboot.storage.db.core.entity.product.response.SellingPossibleStatusPersistenceResponse;

@Repository
public interface ProductRepository {

    /**
     * select * from product where selling_Status in('SELLING','HOLD')
     **/
    List<SellingPossibleStatusPersistenceResponse> findAllBySellingStatusIn(List<ProductSellingStatus> sellingStatuses);

    List<AllFiledProductEntityDTO> findAllByProductNumberIn(List<Long> productNumbers);

    Long findLatesProductNumber();

    List<OrderCreatePersistenceResponse> updateList(List<OrderCreatePersistenceRequest> productDTOList);

    CreateProductPersistenceResponse productRegistration(CreateProductPersistenceRequest request);

}
