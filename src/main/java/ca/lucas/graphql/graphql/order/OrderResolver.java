package ca.lucas.graphql.graphql.order;

import ca.lucas.graphql.domain.Order;
import ca.lucas.graphql.domain.Payment;
import ca.lucas.graphql.service.PaymentService;
import com.coxautodev.graphql.tools.GraphQLResolver;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class OrderResolver implements GraphQLResolver<Order> {

  private PaymentService paymentService;

  public OrderResolver(PaymentService paymentService) {
    this.paymentService = paymentService;
  }

  public List<Payment> getPayments(Order order) {
    return this.paymentService.findAllByOrderId(order.getId());
  }

  public String getCreatedAt(Order order, String format) {
    try {
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
      return formatter.format(order.getCreatedAt());
    } catch (IllegalArgumentException e) {
      return order.getCreatedAt().toString();
    }

  }

}
