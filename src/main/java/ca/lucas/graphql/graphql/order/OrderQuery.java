package ca.lucas.graphql.graphql.order;

import ca.lucas.graphql.domain.Order;
import ca.lucas.graphql.service.OrderService;
import ca.lucas.graphql.service.OrderTemplateService;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

@Component
public class OrderQuery implements GraphQLQueryResolver {

  private final OrderService orderService;
  private final OrderTemplateService orderTemplateService;

  public OrderQuery(OrderService orderService, OrderTemplateService orderTemplateService) {
    this.orderService = orderService;
    this.orderTemplateService = orderTemplateService;
  }

  public List<Order> orders(int count, int offset) {
    return this.orderService.findAll(PageRequest.of(offset, count)).getContent();
  }

  public List<Order> ordersByStatus(String status) {
    return this.orderService.findByStatus(status);
  }

  public List<Order> ordersBetween(LocalDateTime start, LocalDateTime end) {
    return this.orderTemplateService.ordersBetween(start, end);
  }

}
