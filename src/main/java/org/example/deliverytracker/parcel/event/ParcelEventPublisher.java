package org.example.deliverytracker.parcel.event;

import org.example.deliverytracker.kafka.KafkaService;
import org.example.deliverytracker.kafka.Topics;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ParcelEventPublisher extends KafkaService {
    public ParcelEventPublisher(KafkaTemplate<String, Object> kafkaTemplate) {
        super(kafkaTemplate);
    }

    public void publishParcelEvent(ParcelEvent event) {
        publish(Topics.PARCEL_EVENTS_TOPIC, event.trackingNumber(), event);
    }
}
