package io.dodn.springboot.core.test.v1.persistence.product;

import io.dodn.springboot.storage.db.core.entity.product.ProductEntity;
import io.dodn.springboot.storage.db.core.entity.product.ProductJPARepository;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@ComponentScan
class TestProductRepository {

    TestProductRepository(ProductJPARepository productJpaRepository) {
        this.productJpaRepository = productJpaRepository;
    }

    private final ProductJPARepository productJpaRepository;


    public List<TestProductDto> saveAll(List<TestProductDto> productDtoList) {
        List<ProductEntity> entityList = productDtoList.stream().map(TestProductDto::toEntity).toList();
        return productJpaRepository.saveAll(entityList).stream().map(TestProductDto::of).toList();
    }

    public TestProductDto save(TestProductDto productDto) {
        return TestProductDto.of(productJpaRepository.save(productDto.toEntity()));
    }

}
