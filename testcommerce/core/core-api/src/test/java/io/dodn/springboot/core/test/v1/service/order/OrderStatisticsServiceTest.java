package io.dodn.springboot.core.test.v1.service.order;

import io.dodn.springboot.core.domain.order.service.OrderStatisticsService;
import io.dodn.springboot.core.test.example.ContextTest;
import io.dodn.springboot.storage.db.core.entity.order.OrderRepository;
import io.dodn.springboot.storage.db.core.entity.orderproduct.OrderProductRepository;
import io.dodn.springboot.storage.db.core.entity.product.ProductRepository;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

@SpringBootTest
@Profile("test")
class OrderStatisticsServiceTest extends ContextTest {

    private OrderStatisticsService orderStatisticsService;

    private OrderRepository orderRepository;

    private ProductRepository productRepository;

    private OrderProductRepository orderProductRepository;

    // private

    // @MockBean
    // private MailSendClient mailSendClient;
    //
    //
    // @AfterEach
    // void tearDown() {
    // orderProductRepository.deleteAllInBatch();
    // orderRepository.deleteAllInBatch();
    // productRepository.deleteAllInBatch();
    // mailSendHistoryRepository.deleteAllInBatch();
    // }
    //
    // @DisplayName("결제완료 주문들을 조회하여 매출 통계 메일을 전송한다.")
    // @Test
    // void sendOrderStatisticsMail(){
    // // given
    // LocalDateTime now = LocalDateTime.of(2023, 3, 5, 0, 0);
    //
    // Product product1 = createProduct(CLOTHES, "001", 1000);
    // Product product2 = createProduct(CLOTHES, "002", 2000);
    // Product product3 = createProduct(CLOTHES, "003", 3000);
    // List<Product> products = List.of(product1, product2, product3);
    // productRepository.save(products);
    //
    //
    // Order order1 = createPaymentCompletedOrder(products,
    // LocalDateTime.of(2023,3,4,23,59));
    // Order order2 = createPaymentCompletedOrder(products, now);
    // Order order3 = createPaymentCompletedOrder(products,
    // LocalDateTime.of(2023,3,5,23,59));
    // Order order4 = createPaymentCompletedOrder(products,
    // LocalDateTime.of(2023,3,6,0,0));
    //
    //
    // when(mailSendClient.sendEmail(any(String.class),any(String.class),any(String.class),any(String.class)))
    // .thenReturn(true);
    //
    //
    //
    // // when
    // boolean result = orderStatisticsService.sendOrderStatisticsMail(LocalDate.of(2023,
    // 3, 5), "test@test.com");
    //
    // // then
    //
    //
    // assertThat(result).isTrue();
    //
    // List<MailSendHistory> histories = mailSendHistoryRepository.findAll();
    //
    // assertThat(histories).hasSize(1)
    // .extracting("content")
    // .contains("총 매출 합계는 12000원입니다.");
    // }
    //
    // private Order createPaymentCompletedOrder(List<Product> products, LocalDateTime
    // now) {
    // return Order.builder()
    // .products(products)
    // .orderStatus(OrderStatus.PAYMENT_COMPLETED)
    // .registeredDateTime(now)
    // .build();
    // }
    //
    //
    // private Product createProduct(ProductType type, String productNumber,int price){
    // return Product.builder()
    // .productNumber(productNumber)
    // .type(type)
    // .sellingStatus(SELLING)
    // .name("메뉴이름")
    // .price(price)
    // .build();
    //
    // }

}