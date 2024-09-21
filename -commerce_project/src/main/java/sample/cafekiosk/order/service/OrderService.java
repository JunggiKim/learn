package sample.cafekiosk.order.service;


import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sample.cafekiosk.order.Order;
import sample.cafekiosk.order.OrderRepository;
import sample.cafekiosk.order.request.OrderCreateServiceRequest;
import sample.cafekiosk.order.response.OrderResponse;
import sample.cafekiosk.product.Product;
import sample.cafekiosk.product.ProductRepository;
import sample.cafekiosk.stock.StockRepository;
import sample.cafekiosk.stock.service.Stockservice;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Transactional (readOnly = true)
@Service
@RequiredArgsConstructor
public class OrderService {

    private final ProductRepository productRepository;

    private final OrderRepository orderRepository;

    private final StockRepository stockRepository;

    private final Stockservice stockservice;

    private final ApplicationEventPublisher applicationEventPublisher;
    /*
*/
    @Transactional
    public OrderResponse createOrder(OrderCreateServiceRequest request, LocalDateTime registeredDateTime) throws IllegalAccessException {
        List<String> productNumbers = request.getProductNumbers();
        List<Product> products  = findProductsBy(productNumbers);

        stockservice.deductStockQuantities(products);

        Order order = Order.create(products, registeredDateTime,request.getUserEmail());
        Order savedOrder = orderRepository.save(order);
        OrderResponse orderResponse = OrderResponse.of(savedOrder);

        applicationEventPublisher.publishEvent(orderResponse);

        return  orderResponse;
    }

    private List<Product> findProductsBy(List<String> productNumbers) {
        List<Product> products = productRepository.findAllByProductNumberIn(productNumbers);

        Map<String, Product> productMap = products.stream()
                .collect(Collectors.toMap(Product::getProductNumber, product -> product));

        return productNumbers.stream()
                .map(productMap::get)
                .collect(Collectors.toList());
    }


        //list<stirng> = {a, a,b, c }
       //  map<a,2> map<b,1> map<c,1>




}
