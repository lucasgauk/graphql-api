package ca.lucas.graphql.service;

import ca.lucas.graphql.domain.Order;
import ca.lucas.graphql.shared.OrderNotFoundException;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {

  Order findById(String id) throws OrderNotFoundException;

  Order save(Order order);

  Order newOrder(BigDecimal total);

  Page<Order> findAll(Pageable pageable);

  List<Order> findByStatus(String status);

}
