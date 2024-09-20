package io.dodn.springboot.storage.db.core.entity.product;

import java.util.List;

import org.springframework.stereotype.Repository;

import io.dodn.springboot.core.enums.ProductType.ProductSellingStatus;
import io.dodn.springboot.storage.db.core.entity.product.request.CreateProductPersistenceRequest;
import io.dodn.springboot.storage.db.core.entity.product.response.AllFiledProductEntityDTO;
import io.dodn.springboot.storage.db.core.entity.product.request.OrderCreatePersistenceRequest;
import io.dodn.springboot.storage.db.core.entity.product.response.CreateProductPersistenceResponse;
import io.dodn.springboot.storage.db.core.entity.product.response.OrderCreatePersistenceResponse;
import io.dodn.springboot.storage.db.core.entity.product.response.SellingPossibleStatusPersistenceResponse;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
class ProductRepositoryImpl implements ProductRepository {

    private final ProductJPARepository productJPARepository;

    /**
     * select * from product where selling_Status in('SELLING','HOLD')
     **/
    @Override
    public List<SellingPossibleStatusPersistenceResponse> findAllBySellingStatusIn(
            List<ProductSellingStatus> sellingStatuses) {

        List<ProductEntity> entityList = productJPARepository.findAllBySellingStatusIn(sellingStatuses);

        return entityList.stream().map(SellingPossibleStatusPersistenceResponse::of).toList();
    }

    @Override
    public List<AllFiledProductEntityDTO> findAllByProductNumberIn(List<Long> productNumbers) {
        return productJPARepository.findAllByProductNumberIn(productNumbers)
            .stream()
            .map(AllFiledProductEntityDTO::of)
            .toList();

    }

    @Override
    public Long findLatesProductNumber() {
        return productJPARepository.findLatesProductNumber();
    }

    @Override
    public List<OrderCreatePersistenceResponse> updateList(List<OrderCreatePersistenceRequest> productDTOList) {
        List<ProductEntity> entityList = productDTOList.stream().map(OrderCreatePersistenceRequest::toEntity).toList();

        return productJPARepository.saveAll(entityList).stream().map(OrderCreatePersistenceResponse::of).toList();

    }

    @Override
    public CreateProductPersistenceResponse productRegistration(CreateProductPersistenceRequest request) {
        ProductEntity savedEntity = productJPARepository.save(request.toEntity());
        return CreateProductPersistenceResponse.of(savedEntity);
    }

}
