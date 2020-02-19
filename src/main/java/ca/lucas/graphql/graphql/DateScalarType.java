package ca.lucas.graphql.graphql;

import graphql.language.StringValue;
import graphql.schema.Coercing;
import graphql.schema.CoercingParseLiteralException;
import graphql.schema.CoercingParseValueException;
import graphql.schema.CoercingSerializeException;
import graphql.schema.GraphQLScalarType;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import org.springframework.stereotype.Component;

@Component
public class DateScalarType extends GraphQLScalarType {

  DateScalarType() {

    super("Date", "Date value", new Coercing<LocalDateTime, String>() {
      public String serialize(Object o) throws CoercingSerializeException {
        return serializeLocalDateTime(o);
      }

      public LocalDateTime parseValue(Object o) throws CoercingParseValueException {
        return parseLocalDateTimeFromValue(o);
      }

      public LocalDateTime parseLiteral(Object o) throws CoercingParseLiteralException {
        return parseLocalDateTimeFromLiteral(o);
      }

    });
  }

  private static String serializeLocalDateTime(Object o) {
    if (o instanceof LocalDateTime) {
      LocalDateTime dateTime = (LocalDateTime) o;
      return dateTime.toString();
    }

    throw new CoercingSerializeException("Object not an instance of LocalDateTime");
  }

  private static LocalDateTime parseLocalDateTimeFromValue(Object o) {
    if (o instanceof String) {
      String dateTimeString = (String) o;
      try {
        return LocalDateTime.parse(dateTimeString);
      } catch (DateTimeParseException e) {
        throw new CoercingParseValueException("Unable to create LocalDateTime from String");
      }
    }
    throw new CoercingParseValueException("Object not instance of String");
  }

  private static LocalDateTime parseLocalDateTimeFromLiteral(Object o) {
    if (o instanceof StringValue) {
      StringValue dateTimeString = (StringValue) o;
      try {
        return LocalDateTime.parse(dateTimeString.getValue());
      } catch (DateTimeParseException e) {
        throw new CoercingParseValueException("Unable to create LocalDateTime from StringValue");
      }
    }
    throw new CoercingParseValueException("Object not instance of StringValue");
  }

}
