package io.dodn.springboot.core.domain.product.request;

import io.dodn.springboot.core.enums.ProductType.ProductSellingStatus;
import io.dodn.springboot.core.enums.ProductType.ProductType;
import io.dodn.springboot.storage.db.core.entity.product.request.CreateProductPersistenceRequest;

public record ProductCreateServiceRequest(

        ProductType type, ProductSellingStatus sellingStatus, String name, Long price, Long stockQuantity

) {

    public CreateProductPersistenceRequest toPersistenceRequest(Long nextProductNumber) {
        return new CreateProductPersistenceRequest(this.type, this.sellingStatus, this.name, this.price,
                this.stockQuantity, nextProductNumber);
    }
}
