package ca.lucas.graphql.service;

import ca.lucas.graphql.domain.Payment;
import ca.lucas.graphql.shared.OrderNotFoundException;
import java.math.BigDecimal;
import java.util.List;
import org.bson.types.ObjectId;

public interface PaymentService {

  Payment findById(String id);

  Payment save(Payment payment);

  Payment create(String orderId, BigDecimal amount) throws OrderNotFoundException;

  List<Payment> findAllByOrderId(ObjectId orderId);

}
