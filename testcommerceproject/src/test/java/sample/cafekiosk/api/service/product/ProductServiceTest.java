package sample.cafekiosk.api.service.product;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import sample.cafekiosk.product.request.ProductCreateRequest;
import sample.cafekiosk.product.response.ProductResponse;
import sample.cafekiosk.domain.product.Product;
import sample.cafekiosk.domain.product.ProductRepository;
import sample.cafekiosk.domain.product.ProductType.ProductSellingStatus;
import sample.cafekiosk.domain.product.ProductType.ProductType;
import sample.cafekiosk.product.service.ProductService;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static sample.cafekiosk.domain.product.ProductType.ProductSellingStatus.*;
import static sample.cafekiosk.domain.product.ProductType.ProductType.HANDMADE;

@ActiveProfiles("test")
@SpringBootTest
class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    @AfterEach
    void tearDown() {
        productRepository.deleteAllInBatch();
    }

     // 동시성 이슈가 있다  동시에 같은등록을 하는 경우 상품번호가 다른상품에 먼저들어간경우 자동으로 3번의 재시도를 디비에하게 만들어두는 로직
     // UUID 로 유니크한 키를 만들면 동시성관련문제없이 사용가능하다.
    @DisplayName("신규 상품을 등록한다. 상품번호는 가장 최근 상품의 상품번호에서 1증가한 값이다.")
    @Test
    void createProduct() {
        //  given
        Product product = createProduct("001", HANDMADE, SELLING, "아메리카노", 4000);
        productRepository.save(product);

        ProductCreateRequest request = ProductCreateRequest.builder()
                .type(HANDMADE)
                .sellingStatus(SELLING)
                .name("카푸치노")
                .price(5000)
                .build();
        //  when
        ProductResponse productResponse = productService.createProduct(request.toServiceRequest());

        //  then
        assertThat(productResponse)
                .extracting("productNumber","type","sellingStatus","name","price")
                .contains("002",HANDMADE,SELLING,"카푸치노",5000);



        List<Product> products = productRepository.findAll();
        assertThat(products).hasSize(2)
                .extracting("productNumber","type","sellingStatus","name","price")
                .containsExactlyInAnyOrder(
                        tuple("001",HANDMADE,SELLING,"아메리카노",4000),
                        tuple("002",HANDMADE,SELLING,"카푸치노",5000)

                );

    }



    @DisplayName("상품이 하나도 없는 경우 신규 상품을 등록 하면 상품번호는 001이다")
    @Test
    void createProductWhenProductsIsEmpty() {
        //  given
        ProductCreateRequest request = ProductCreateRequest.builder()
                .type(HANDMADE)
                .sellingStatus(SELLING)
                .name("카푸치노")
                .price(5000)
                .build();
        //  when
        ProductResponse productResponse = productService.createProduct(request.toServiceRequest());

        //  then
        assertThat(productResponse)
                .extracting("productNumber","type","sellingStatus","name","price")
                .contains("001",HANDMADE,SELLING,"카푸치노",5000);


        List<Product> products = productRepository.findAll();
        assertThat(products).hasSize(1)
                .extracting("productNumber","type","sellingStatus","name","price")
                .contains(
                        tuple("001",HANDMADE,SELLING,"카푸치노",5000)

                );

    }


//    @Test
//    void getSellingProducts() {
//    }



    private Product createProduct(String productNumber, ProductType type,ProductSellingStatus productSellingStatus , String name,int price) {
        return Product.builder()
                .productNumber(productNumber)
                .type(type)
                .sellingStatus(productSellingStatus)
                .name(name)
                .price(price)
                .build();

    }



}
