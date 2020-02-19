package ca.lucas.graphql.shared;

import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;
import java.util.List;

public class OrderNotFoundException extends RuntimeException implements GraphQLError {

  public OrderNotFoundException(String orderId) {
    super("Order id: " + orderId + " not found");
  }

  public List<SourceLocation> getLocations() {
    return null;
  }

  public ErrorType getErrorType() {
    return ErrorType.DataFetchingException;
  }

}

