package io.dodn.springboot.storage.db.core.entity.product;

import io.dodn.springboot.core.enums.ProductType.ProductSellingStatus;
import io.dodn.springboot.core.enums.ProductType.ProductType;
import io.dodn.springboot.storage.db.core.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "productEntity")
public class ProductEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long productNumber;

    @Enumerated(EnumType.STRING)
    private ProductType type;

    @Enumerated(EnumType.STRING)
    private ProductSellingStatus sellingStatus;

    private String name;

    private Long price;

    private Long stockQuantity;

    @Builder
    private ProductEntity(Long productNumber, Long id, ProductType type, ProductSellingStatus sellingStatus,
            String name, Long price, Long stockQuantity) {
        this.id = id;
        this.type = type;
        this.sellingStatus = sellingStatus;
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.productNumber = productNumber;
    }

}
