// package io.dodn.springboot.core.domain.mail;
//
// import io.dodn.springboot.core.domain.order.response.CreateOrderResponse;
// import org.springframework.stereotype.Service;
// import org.springframework.transaction.annotation.Transactional;
//
// import lombok.RequiredArgsConstructor;
// import lombok.extern.slf4j.Slf4j;
//
// @Service
// @RequiredArgsConstructor
// @Transactional
// @Slf4j
// public class MailService {
//
// private final MailSendHistoryRepository mailSendHistoryRepository;
//
// // private final JavaMailSender mailSender;
//
// // @Value("${spring.mail.username}")
// private String fromEmail;
//
// public boolean orderCreateMail(CreateOrderResponse createOrderResponse) {
// // SimpleMailMessage emailForm = orderCreateEmailForm(createOrderResponse);
// // try {
// // mailSender.send(emailForm);
// // log.info("이메일 전송: {}", createOrderResponse.getUserEmail());
// // } catch (RuntimeException e) {
// // log.debug("MailService.sendEmail 이메일 전송 실패 이메일: {}, " +
// // "주문id: {}", createOrderResponse.getUserEmail(), createOrderResponse.getOrderId());
// // log.error("에러 메시지 = {} ", e.getMessage());
// // throw new RuntimeException("이메일 전송에 실패했습니다.");
// // }
//
// return true;
// }
//
//
// private SimpleMailMessage orderCreateEmailForm(CreateOrderResponse createOrderResponse)
// {
// SimpleMailMessage message = new SimpleMailMessage();
// message.setTo(createOrderResponse.getUserEmail());
// message.setSubject("주문이 완료되었습니다.");
// message.setFrom(fromEmail);
// StringBuffer str = new StringBuffer();
// str.append("주문 번호: " + createOrderResponse.getId() + "\n")
// .append("총 가격: " + createOrderResponse.getTotalPrice() + "\n")
// .append("주문 날짜: " + createOrderResponse.getRegisteredDateTime() + "\n");
// createOrderResponse.getProducts().forEach(product -> str.append("주문 상품 = "+
// product.getName() + "\n"));
// str.append("주문 개수: " + createOrderResponse.getProducts().size() + "\n");
//
//
// message.setText(str.toString());
// return message;
// }
//
//
// }