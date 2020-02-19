package ca.lucas.graphql.service.impl;

import ca.lucas.graphql.OrderStatus;
import ca.lucas.graphql.domain.Order;
import ca.lucas.graphql.repository.OrderRepository;
import ca.lucas.graphql.service.OrderService;
import ca.lucas.graphql.shared.OrderNotFoundException;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import org.apache.commons.lang3.EnumUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

  private OrderRepository orderRepository;

  public OrderServiceImpl(OrderRepository orderRepository) {
    this.orderRepository = orderRepository;
  }

  public Order findById(String id) {
    return this.orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException(id));
  }

  public Order save(Order order) {
    return this.orderRepository.save(order);
  }

  public Order newOrder(BigDecimal total) {
    Order order = Order.builder().total(total).build();
    return this.save(order);
  }

  public Page<Order> findAll(Pageable pageable) {
    return this.orderRepository.findAll(pageable);
  }

  public List<Order> findByStatus(String status) {
    if (EnumUtils.isValidEnum(OrderStatus.class, status)) {
      return Collections.emptyList();
    }
    return this.orderRepository.findAllByStatus(OrderStatus.valueOf(status));
  }

}
