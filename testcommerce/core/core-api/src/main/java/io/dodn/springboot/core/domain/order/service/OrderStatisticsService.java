package io.dodn.springboot.core.domain.order.service;

import org.springframework.stereotype.Service;

import io.dodn.springboot.storage.db.core.entity.order.OrderRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class OrderStatisticsService {

    private final OrderRepository orderRepository;

    // private final MailService mailService;

    // public boolean sendOrderStatisticsMail(LocalDate orderDate,String email){
    // //해당 일자에 결제완료된 주문들을 가져와서


    // List<OrderEntity> orders = orderRepository.findOrdersBy(
    // orderDate.atStartOfDay(),
    // orderDate.plusDays(1).atStartOfDay(),
    // OrderStatus.PAYMENT_COMPLETED
    // );
    //
    // //총 매출 합계를 계산
    // int totalAmount = orders.stream()
    // .mapToInt(Order::calculateTotalPrice)
    // .sum();
    //
    // 메일 전송
    // boolean result = mailService.sendMail(
    // "no_rply@cafekiosk.com",
    // email,
    // String.format("[매출통계] %s",orderDate),
    // String.format("총 매출 합계는 %s원입니다.",totalAmount));
    // if(!result){
    // throw new IllegalArgumentException("매출 통계 메일 전송에 실패했습니다.");
    // }
    // return true;
    // }

}
