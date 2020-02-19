package ca.lucas.graphql.domain;

import ca.lucas.graphql.OrderStatus;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@TypeAlias("Order")
@Builder
public class Order {

  @Id
  private ObjectId id;
  @Default private OrderStatus status = OrderStatus.IN_PROGRESS;
  private BigDecimal total;
  @CreatedDate private LocalDateTime createdAt;

}
