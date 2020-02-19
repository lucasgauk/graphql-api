package ca.lucas.graphql.service.impl;

import ca.lucas.graphql.OrderStatus;
import ca.lucas.graphql.domain.Order;
import ca.lucas.graphql.domain.Payment;
import ca.lucas.graphql.repository.PaymentRepository;
import ca.lucas.graphql.service.OrderService;
import ca.lucas.graphql.service.PaymentService;
import ca.lucas.graphql.shared.OrderNotFoundException;
import java.math.BigDecimal;
import java.util.List;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

  private PaymentRepository paymentRepository;
  private OrderService orderService;

  public PaymentServiceImpl(PaymentRepository paymentRepository, OrderService orderService) {
    this.paymentRepository = paymentRepository;
    this.orderService = orderService;
  }

  public Payment findById(String id) {
    return this.paymentRepository.findById(id).orElse(null);
  }

  public Payment save(Payment payment) {
    return this.paymentRepository.save(payment);
  }

  public Payment create(String orderId, BigDecimal amount) {
    Order order = this.orderService.findById(orderId);
    if (order == null) {
      throw new OrderNotFoundException(orderId);
    }

    Payment payment = Payment.builder().orderId(order.getId()).amount(amount).build();

    payment = this.save(payment);

    this.verifyOrderStatus(order);

    return payment;
  }

  public List<Payment> findAllByOrderId(ObjectId orderId) {
    return this.paymentRepository.findAllByOrderId(orderId);
  }

  private void verifyOrderStatus(Order order) {
    List<Payment> appliedPayments = this.findAllByOrderId(order.getId());

    BigDecimal paidAmount = appliedPayments.stream()
                                           .map(Payment::getAmount)
                                           .reduce(BigDecimal::add)
                                           .orElse(BigDecimal.ZERO);

    if (paidAmount.compareTo(order.getTotal()) > 0) {
      order.setStatus(OrderStatus.COMPLETED);
    }

    this.orderService.save(order);
  }

}
