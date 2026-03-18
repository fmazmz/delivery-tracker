package org.example.deliverytracker.kafka;

import org.example.deliverytracker.kafka.event.ParcelEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaService {

    private static final Logger logger = LoggerFactory.getLogger(KafkaService.class);
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public KafkaService(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publish(String topic, String key, Object event) {
        kafkaTemplate.send(topic, key, event);
        logger.info("Published event to topic={}, key={}, type={}", topic, key, event.getClass().getSimpleName());
    }

    public void publishParcelEvent(ParcelEvent event) {
        publish(KafkaConfig.PARCEL_EVENTS_TOPIC, event.trackingNumber(), event);
    }
}
