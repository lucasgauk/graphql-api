package ca.lucas.graphql.repository;

import ca.lucas.graphql.OrderStatus;
import ca.lucas.graphql.domain.Order;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {

  List<Order> findAllByStatus(OrderStatus status);

}
