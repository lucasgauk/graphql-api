package ca.lucas.graphql.graphql.payment;

import ca.lucas.graphql.domain.Payment;
import ca.lucas.graphql.service.PaymentService;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import java.math.BigDecimal;
import org.springframework.stereotype.Component;

@Component
public class PaymentMutation implements GraphQLMutationResolver {

  private final PaymentService paymentService;

  public PaymentMutation(PaymentService paymentService) {
    this.paymentService = paymentService;
  }

  public Payment createPayment(String orderId, BigDecimal amount) {
    return this.paymentService.create(orderId, amount);
  }

}
