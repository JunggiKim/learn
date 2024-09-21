package com.example.userservice.client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

import com.example.userservice.error.FeignErrorDecoder;
import com.example.userservice.vo.ResponseOrder;

@FeignClient(name="order-service", configuration = FeignErrorDecoder.class)
public interface OrderServiceClient {

	@GetMapping("/order-service/{userId}/orders")
		//    @GetMapping("/order-service/{userId}/orders_wrong")
		//    ResponseEntity<List<ResponseOrder>> getOrders(@PathVariable String userId);
	List<ResponseOrder> getOrders(@PathVariable String userId);
}