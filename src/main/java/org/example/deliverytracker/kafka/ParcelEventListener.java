package org.example.deliverytracker.kafka;

import org.example.deliverytracker.kafka.event.ParcelEvent;
import org.example.deliverytracker.parcel.ParcelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ParcelEventListener {

    private static final Logger logger = LoggerFactory.getLogger(ParcelEventListener.class);
    private final ParcelService parcelService;

    public ParcelEventListener(ParcelService parcelService) {
        this.parcelService = parcelService;
    }

    @KafkaListener(topics = KafkaConfig.PARCEL_EVENTS_TOPIC)
    void onParcelEvent(ParcelEvent event) {
        logger.info("Received ParcelEvent - Tracking: {}, Status: {}, Location: {}",
                event.trackingNumber(), event.status(), event.location());

        parcelService.updateStatus(event.trackingNumber(), event.status(), event.location());
    }
}

