package io.dodn.springboot.core.domain.product.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import io.dodn.springboot.core.domain.order.dto.AllFieldProductDTO;
import io.dodn.springboot.core.domain.order.request.OrderCreateServiceRequest;
import io.dodn.springboot.core.domain.product.Product;
import io.dodn.springboot.core.domain.product.ProductConvert;
import io.dodn.springboot.core.enums.ProductType.ProductType;
import io.dodn.springboot.storage.db.core.entity.product.ProductRepository;
import io.dodn.springboot.storage.db.core.entity.product.response.OrderCreatePersistenceResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class ProductBusiness {

    private final ProductRepository productRepository;

    private final ProductConvert productConvert;

    private static Map<Integer, Long> createCuntingMapBy(List<Integer> stockProductNumbers) {
        return stockProductNumbers.stream()
            .collect(Collectors.groupingBy(productNumber -> productNumber, Collectors.counting()));

    }

    private static List<Long> extractStockProductNumbers(List<Product> products) {
        return products.stream()
            .filter(product -> ProductType.isClothes(product.type()))
            .map(Product::productNumber)
            .collect(Collectors.toList());
    }

    /*
     * 주문이 들어온 주문번호들을 그룹 바이해서 번호의 맞게 수량 제거를 하자 그걸 위해서는 번호와 개수의 맵, 주문번호와 상품도메인의 맵 이 두개를 만들고
     * 수량제거를 하는 로직을 세워보자
     */
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public List<OrderCreatePersistenceResponse> productDeductQuantities(
            List<OrderCreateServiceRequest.productDTO> productDTOs) throws IllegalAccessException {

        // 시간 복잡도를 위해 product Map 만들기
        Map<Long, Product> productQuantityMap = getProductQuantityMap(productDTOs);

        // 재고 차감
        List<AllFieldProductDTO> deductProductDTOList = quantityDeduct(productDTOs, productQuantityMap);

        // 차감한 재고 반영
        return productRepository.updateList(productConvert.toOrderCreatePersistenceRequest(deductProductDTOList));

    }

    private List<AllFieldProductDTO> quantityDeduct(List<OrderCreateServiceRequest.productDTO> productDTOs,
            Map<Long, Product> productQuantityMap) throws IllegalAccessException {

        List<Product> deductProduct = new ArrayList<>();

        for (OrderCreateServiceRequest.productDTO productDTO : productDTOs) {
            Long quantity = productDTO.quantity();
            Product product = productQuantityMap.get(productDTO.productId());

            product.deductQuantity(quantity);

            deductProduct.add(product);
        }

        return deductProduct.stream().map(AllFieldProductDTO::of).toList();
    }

    private Map<Long, Product> getProductQuantityMap(List<OrderCreateServiceRequest.productDTO> productDTOs) {
        List<Product> productList = findProductsBy(productDTOs);

        return productList.stream().collect(Collectors.toMap(Product::productId, product -> product));

    }

    public List<Product> findProductsBy(List<OrderCreateServiceRequest.productDTO> productDTOS) {
        List<Long> productNumbers = productDTOS.stream().map(OrderCreateServiceRequest.productDTO::productId).toList();

        return productConvert.toDomainList(productRepository.findAllByProductNumberIn(productNumbers));
    }

}
