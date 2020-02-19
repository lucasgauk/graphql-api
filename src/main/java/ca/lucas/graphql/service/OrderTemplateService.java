package ca.lucas.graphql.service;

import ca.lucas.graphql.domain.Order;
import java.time.LocalDateTime;
import java.util.List;

public interface OrderTemplateService {

  List<Order> ordersBetween(LocalDateTime start, LocalDateTime end);

}
