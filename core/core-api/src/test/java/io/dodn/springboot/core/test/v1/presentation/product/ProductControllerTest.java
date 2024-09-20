package io.dodn.springboot.core.test.v1.presentation.product;// package
                                                             // io.dodn.springboot.test.api.v1.presentation.product;

//
// import static org.springframework.http.MediaType.*;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
// import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
// import org.junit.jupiter.api.DisplayName;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
// import org.springframework.boot.test.mock.mockito.MockBean;
// import org.springframework.test.web.servlet.MockMvc;
//
// import com.fasterxml.jackson.databind.ObjectMapper;
//
// import io.dodn.springboot.core.api.controller.v1.ProductController;
// import io.dodn.springboot.core.api.controller.v1.request.ProductCreateRequest;
// import io.dodn.springboot.core.domain.product.service.ProductService;
// import io.dodn.springboot.core.enums.ProductType.ProductSellingStatus;
// import io.dodn.springboot.core.enums.ProductType.ProductType;
//
//
//
// @WebMvcTest(controllers = ProductController.class)
// class ProductControllerTest {
//
//
// @Autowired
// private ObjectMapper objectMapper;
//
// @Autowired
// private MockMvc mockMvc;
//
// @MockBean
// private ProductService productService;
//
//
//
// @DisplayName("신규 상품을 생성한다.")
// @Test
//
// void createProduct() throws Exception {
// // given
//
// ProductCreateRequest request = ProductCreateRequest.builder()
// .type(ProductType.CLOTHES)
// .sellingStatus(ProductSellingStatus.SELLING)
// .name("아메리카노")
// .price(4000)
// .build();
// // when then
//
// mockMvc.perform(post("/api/v1/products/new")
// .content(objectMapper.writeValueAsString(request))
// .contentType(APPLICATION_JSON)
// )
// .andDo(print())
// .andExpect(status().isOk());
//
//
// }
//
//
// @DisplayName("신규 상품을 등록할 때 상품 타입은 필수값이다.")
// @Test
// void createProductWithhoutType() throws Exception {
// // given
//
// ProductCreateRequest request = ProductCreateRequest.builder()
// .sellingStatus(ProductSellingStatus.SELLING)
// .name("아메리카노")
// .price(4000)
// .build();
// // when then
//
// mockMvc.perform(post("/api/v1/products/new")
// .content(objectMapper.writeValueAsString(request))
// .contentType(APPLICATION_JSON)
// )
// .andDo(print())
// .andExpect(status().isBadRequest())
// .andExpect(jsonPath("$.code").value("400"))
// .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
// .andExpect(jsonPath("$.message").value("상품 타입은 필수입니다."))
// .andExpect(jsonPath("$.data").isEmpty());
//
// }
//
//
// @DisplayName("신규 상품을 등록할 때 상품 판매상태는 필수값이다.")
// @Test
// void createProductWithoutSEllingStatus() throws Exception {
// // given
// ProductCreateRequest request = ProductCreateRequest.builder()
// .type(ProductType.ELECTRONIC_PRODUCTS)
// .name("아메리카노")
// .price(4000)
// .build();
// // when then
//
// mockMvc.perform(post("/api/v1/products/new")
// .content(objectMapper. writeValueAsString(request))
// .contentType(APPLICATION_JSON)
// )
// .andDo(print())
// .andExpect(status().isBadRequest())
// .andExpect(jsonPath("$.code").value("400"))
// .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
// .andExpect(jsonPath("$.message").value("상품 판매상태는 필수입니다."))
// .andExpect(jsonPath("$.data").isEmpty());
//
// }
//
//
// @DisplayName("신규 상품을 등록할 때 상품 이름은 필수값이다.")
// @Test
// void createProductWithoutType() throws Exception {
// // given
// ProductCreateRequest request = ProductCreateRequest.builder()
// .sellingStatus(ProductSellingStatus.SELLING)
// .type(ProductType.ELECTRONIC_PRODUCTS)
// .price(4000)
// .build();
// // when then
//
// mockMvc.perform(post("/api/v1/products/new")
// .content(objectMapper. writeValueAsString(request))
// .contentType(APPLICATION_JSON)
// )
// .andDo(print())
// .andExpect(status().isBadRequest())
// .andExpect(jsonPath("$.code").value("400"))
// .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
// .andExpect(jsonPath("$.message").value("상품 이름은 필수입니다."))
// .andExpect(jsonPath("$.data").isEmpty());
//
// }
//
//
//
// @DisplayName("신규 상품을 등록할 때 상품 갸격은 양수이다.")
// @Test
// void createProductWithZeroPrice() throws Exception {
// // given
// ProductCreateRequest request = ProductCreateRequest.builder()
// .type(ProductType.ELECTRONIC_PRODUCTS)
// .sellingStatus(ProductSellingStatus.SELLING)
// .name("아메리카노")
// .price(0)
// .build();
// // when then
//
// mockMvc.perform(post("/api/v1/products/new")
// .content(objectMapper. writeValueAsString(request))
// .contentType(APPLICATION_JSON)
// )
// .andDo(print())
// .andExpect(status().isBadRequest())
// .andExpect(jsonPath("$.code").value("400"))
// .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
// .andExpect(jsonPath("$.message").value("상품 가격은 양수여야 합니다."))
// .andExpect(jsonPath("$.data").isEmpty());
//
// }
//
//
// // @DisplayName("판매 상품을 조회한다.")
// // @Test
// // void getSellingProducts() throws Exception {
// // // given
// // List<CreateProductResponse> result = List.of();
// // when(productService.getSellingProducts()).thenReturn(result);
// //
// // // when then
// // mockMvc.perform(get("/api/v1/products/selling")
// // )
// // .andDo(print())
// // .andExpect(status().isOk())
// // .andExpect(jsonPath("$.code").value("200"))
// // .andExpect(jsonPath("$.status").value("OK"))
// // .andExpect(jsonPath("$.message").value("OK"))
// // .andExpect(jsonPath("$.data").isArray());
// // }
//
//
//
//
//
// }