package ca.lucas.graphql;

import ca.lucas.graphql.infrastructure.ObjectIdSerializer;
import com.coxautodev.graphql.tools.SchemaParserOptions;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.bson.types.ObjectId;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
public class GraphqlApplication {

  public static void main(String[] args) {
    SpringApplication.run(GraphqlApplication.class, args);
  }

  @Bean
  public Module objectIdModule() {
    SimpleModule module = new SimpleModule();
    return module.addSerializer(ObjectId.class, new ObjectIdSerializer());
  }

  /**
   * GraphQL uses its own ObjectMapper,
   * therefore you have to register the JavaTimeModule on it.
   */
  @Bean
  public SchemaParserOptions schemaParserOptions() {
    return SchemaParserOptions.newOptions().objectMapperConfigurer((mapper, context) -> {
      mapper.registerModule(new JavaTimeModule());
    }).build();
  }

}
