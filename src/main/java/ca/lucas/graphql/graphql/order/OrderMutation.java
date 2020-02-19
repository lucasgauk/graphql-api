package ca.lucas.graphql.graphql.order;

import ca.lucas.graphql.domain.Order;
import ca.lucas.graphql.service.OrderService;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import java.math.BigDecimal;
import org.springframework.stereotype.Component;

@Component
public class OrderMutation implements GraphQLMutationResolver {

  private OrderService orderService;

  public OrderMutation(OrderService orderService) {
    this.orderService = orderService;
  }

  public Order createOrder(BigDecimal total) {
    return this.orderService.newOrder(total);
  }

}
