package io.dodn.springboot.core.enums.ProductType;

import java.util.List;
import java.util.Objects;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ProductType {

    CLOTHES("의류"),

    FOOD("음식"),

    ELECTRONIC_PRODUCTS("전자제품");

    private final String text;

    public static boolean isElectronicProducts(ProductType type) {
        return Objects.equals(ELECTRONIC_PRODUCTS, type);
    }

    public static boolean isFood(ProductType type) {
        return Objects.equals(FOOD, type);
    }

    public static boolean isClothes(ProductType type) {
        return Objects.equals(CLOTHES, type);
    }

}
