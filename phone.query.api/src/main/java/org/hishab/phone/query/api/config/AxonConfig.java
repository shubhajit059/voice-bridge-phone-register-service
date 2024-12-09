package org.hishab.phone.query.api.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.axonframework.common.transaction.NoTransactionManager;
import org.axonframework.eventhandling.TrackingEventProcessorConfiguration;
import org.axonframework.eventhandling.tokenstore.TokenStore;
import org.axonframework.messaging.StreamableMessageSource;
import org.axonframework.serialization.Serializer;
import org.axonframework.serialization.json.JacksonSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.concurrent.TimeUnit;

@Configuration
public class AxonConfig {

    @Value("${spring.data.mongodb.uri}")
    private String mongoUrl;

    @Value("${spring.data.mongodb.database}")
    private String database;


    @Bean
    @Primary
    public Serializer defaultSerializer() {
        ObjectMapper mapper = JacksonSerializer.defaultSerializer().getObjectMapper();
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        mapper.activateDefaultTyping(
                mapper.getPolymorphicTypeValidator(),
                ObjectMapper.DefaultTyping.OBJECT_AND_NON_CONCRETE
        );

        return JacksonSerializer.builder()
                .objectMapper(mapper)
                .lenientDeserialization()
                .build();
    }

    @Bean
    public Serializer eventSerializer() {
        ObjectMapper mapper = new ObjectMapper();
        return JacksonSerializer.builder().objectMapper(mapper).build();
    }

    //newly added for token storage-start

    /*
    @Bean
    public TrackingEventProcessorConfiguration trackingEventProcessorConfiguration(StreamableMessageSource<?> messageSource) {
        return TrackingEventProcessorConfiguration
                .forParallelProcessing(3)
                .andBatchSize(1000)
                .andTokenClaimInterval(10, TimeUnit.SECONDS);
    }

    @Bean
    public MongoClient mongoClient() {
        return MongoClients.create(mongoUrl);
    }

    @Bean
    public MongoTemplate axonMongoTemplate(MongoClient mongoClient) {
        // Creating Axon's MongoTemplate targeting a specific database
        return DefaultMongoTemplate.builder()
                .mongoDatabase(mongoClient, database) // Specify the database name
                .build();
    }

    @Bean
    public TokenStore tokenStore(MongoTemplate mongoTemplate, Serializer eventSerializer) {
        return MongoTokenStore.builder()
                .mongoTemplate(mongoTemplate)
                .serializer(eventSerializer)
                .transactionManager(NoTransactionManager.instance())
                .build();
    }

    */

    //newly added for token storage-end

}
