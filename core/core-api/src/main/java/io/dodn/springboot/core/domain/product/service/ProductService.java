package io.dodn.springboot.core.domain.product.service;

import java.util.List;
import java.util.stream.Collectors;

import io.dodn.springboot.core.domain.product.request.ProductCreateServiceRequest;
import io.dodn.springboot.core.domain.product.response.CreateProductResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.dodn.springboot.core.domain.product.response.SellingPossibleStatusResponse;
import io.dodn.springboot.core.enums.ProductType.ProductSellingStatus;
import io.dodn.springboot.storage.db.core.entity.product.ProductRepository;
import io.dodn.springboot.storage.db.core.entity.product.response.CreateProductPersistenceResponse;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional
    public CreateProductResponse createProduct(ProductCreateServiceRequest request) {
        Long nextProductNumber = createNextProductNumber();

        CreateProductPersistenceResponse response = productRepository
            .productRegistration(request.toPersistenceRequest(nextProductNumber));

        return CreateProductResponse.of(response);
    }

    private Long createNextProductNumber() {
        Long latesProductNumber = productRepository.findLatesProductNumber();
        return latesProductNumber + 1;
    }

    public List<SellingPossibleStatusResponse> getSellingProducts() {
        return productRepository.findAllBySellingStatusIn(ProductSellingStatus.forDisplay())
            .stream()
            .map(SellingPossibleStatusResponse::of)
            .collect(Collectors.toList());
    }

}
