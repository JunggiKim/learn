package io.dodn.springboot.core.domain.product;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Component;

import io.dodn.springboot.core.domain.order.dto.AllFieldProductDTO;
import io.dodn.springboot.storage.db.core.entity.product.response.AllFiledProductEntityDTO;
import io.dodn.springboot.storage.db.core.entity.product.request.OrderCreatePersistenceRequest;

@Component
public class ProductConvert {

    public List<Product> toDomainList(List<AllFiledProductEntityDTO> productDTOS) {
        return productDTOS.stream().map(this::toDomain).toList();
    }

    public Product toDomain(AllFiledProductEntityDTO allFiledProductEntityDTO) {
        return Product.builder()
            .productId(allFiledProductEntityDTO.productId())
            .type(allFiledProductEntityDTO.type())
            .sellingStatus(allFiledProductEntityDTO.sellingStatus())
            .name(allFiledProductEntityDTO.name())
            .price(BigDecimal.valueOf(allFiledProductEntityDTO.price()))
            .stockQuantity(allFiledProductEntityDTO.quantity())
            .build();
    }

    public List<OrderCreatePersistenceRequest> toOrderCreatePersistenceRequest(
            List<AllFieldProductDTO> productDTOList) {
        return productDTOList.stream()
            .map(productDTO -> new OrderCreatePersistenceRequest(productDTO.productNumber(), productDTO.type(),
                    productDTO.sellingStatus(), productDTO.name(), productDTO.price(), productDTO.quantity()))
            .toList();
    }

}
