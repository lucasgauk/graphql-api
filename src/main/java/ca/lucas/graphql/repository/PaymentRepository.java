package ca.lucas.graphql.repository;

import ca.lucas.graphql.domain.Payment;
import java.util.List;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends MongoRepository<Payment, String> {

  List<Payment> findAllByOrderId(ObjectId orderId);

}
