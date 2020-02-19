package ca.lucas.graphql.service.impl;

import ca.lucas.graphql.domain.Order;
import ca.lucas.graphql.service.OrderTemplateService;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

@Service
public class OrderTemplateServiceImpl implements OrderTemplateService {

  private MongoTemplate mongoTemplate;

  public OrderTemplateServiceImpl(MongoTemplate mongoTemplate) {
    this.mongoTemplate = mongoTemplate;
  }

  public List<Order> ordersBetween(LocalDateTime start, LocalDateTime end) {
    Query query = new Query();
    query.addCriteria(new Criteria().andOperator(Criteria.where("createdAt")
                                                         .gte(start.toInstant(ZoneOffset.UTC)),
                                                 Criteria.where("createdAt").lte(end.toInstant(ZoneOffset.UTC))));
    return this.mongoTemplate.find(query, Order.class, "order");
  }

}
