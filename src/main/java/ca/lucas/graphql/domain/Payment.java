package ca.lucas.graphql.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@TypeAlias("Payment")
@Builder
public class Payment {

  @Id private ObjectId id;
  private ObjectId orderId;
  private BigDecimal amount;
  @CreatedDate LocalDateTime createdAt;

}
