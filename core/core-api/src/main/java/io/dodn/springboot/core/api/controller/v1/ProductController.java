package io.dodn.springboot.core.api.controller.v1;

import java.util.List;

import io.dodn.springboot.core.domain.product.response.SellingPossibleStatusResponse;
import io.dodn.springboot.core.domain.product.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.dodn.springboot.core.api.controller.v1.request.ProductCreateRequest;
import io.dodn.springboot.core.domain.product.response.CreateProductResponse;
import io.dodn.springboot.core.api.support.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/api/v1/products/new")
    public ApiResponse<CreateProductResponse> createProduct(@Valid @RequestBody ProductCreateRequest request) {
        return ApiResponse.success(productService.createProduct(request.toServiceRequest()));
    }

    @GetMapping("/api/v1/products/selling")
    public ApiResponse<List<SellingPossibleStatusResponse>> getSellingProducts() {
        return ApiResponse.success(productService.getSellingProducts());
    }

}
