package io.dodn.springboot.core.enums.ProductType;

import java.util.List;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ProductSellingStatus {

    SELLING("판매 중"), HOLD("판매 보류"), STOP_SELLING("판매 중지");

    private final String text;

    // 화면 에는 판매 중이거나 판매 보류중인 상푸만 보여야 한다.
    public static List<ProductSellingStatus> forDisplay() {
        return List.of(SELLING, HOLD);
    }

}
