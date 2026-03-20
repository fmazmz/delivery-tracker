package org.example.deliverytracker.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicProvider {

    @Bean
    public NewTopic parcelEventsTopic() {
        return TopicBuilder.name(Topics.PARCEL_EVENTS_TOPIC)
                .partitions(3)
                .replicas(1)
                .build();
    }
}
