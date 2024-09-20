package io.dodn.springboot.core.test.v1.presentation.order;// package
                                                           // io.dodn.springboot.test.api.v1.api.controller.order;

//
// import static org.springframework.http.MediaType.*;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
// import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
// import java.util.List;
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
// import io.dodn.springboot.core.api.controller.v1.OrderController;
// import io.dodn.springboot.core.api.controller.v1.request.OrderCreateRequest;
// import io.dodn.springboot.core.domain.order.service.OrderService;
// import io.dodn.springboot.test.api.example.ContextTest;
//
// @WebMvcTest(controllers = OrderController.class)
// class OrderControllerTest extends ContextTest {
//
//
// private MockMvc mockMvc;
//
// private ObjectMapper objectMapper;
//
// @MockBean
// private OrderService orderService;
//
//
// @DisplayName("신규 주문을 등록한다.")
// @Test
// void createOrder() throws Exception {
// // given
// OrderCreateRequest request = new OrderCreateRequest()
// .productNumbers(List.of("001"))
// .build();
//
// // when then
// mockMvc.perform(
// post("/api/v1/orders/new")
// .content(objectMapper.writeValueAsString(request))
// .contentType(APPLICATION_JSON)
// )
// .andDo(print())
// .andExpect(status().isOk())
// .andExpect(jsonPath("$.code").value("200"))
// .andExpect(jsonPath("$.status").value("OK"))
// .andExpect(jsonPath("$.message").value("OK"));
//
// }
//
//
// @DisplayName("신규 주문을 등록할때 상품번호는 1개 이상이어야한다.")
// @Test
// void createOrderWithEmptyProductNumbers() throws Exception {
// // given
// OrderCreateRequest request = OrderCreateRequest.builder()
// .productNumbers(List.of())
// .build();
// // when then
//
// mockMvc.perform(
// post("/api/v1/orders/new")
// .content(objectMapper.writeValueAsString(request))
// .contentType(APPLICATION_JSON)
// )
// .andDo(print())
// .andExpect(status().isBadRequest())
// .andExpect(jsonPath("$.code").value("400"))
// .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
// .andExpect(jsonPath("$.message").value("상품 번호 리스트는 필수입니다."))
// .andExpect(jsonPath("$.data").isEmpty());
// }
//
//
// }