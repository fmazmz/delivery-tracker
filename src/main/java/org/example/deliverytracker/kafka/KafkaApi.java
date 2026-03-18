package org.example.deliverytracker.kafka;

import org.example.deliverytracker.kafka.event.ParcelEvent;
import org.example.deliverytracker.util.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/kafka")
public class KafkaApi {

    private final KafkaService kafkaService;

    public KafkaApi(KafkaService kafkaService) {
        this.kafkaService = kafkaService;
    }

    @PostMapping("/parcel-events")
    public ResponseEntity<ApiResponse<String>> publishParcelEvent(@RequestBody ParcelEvent event) {
        kafkaService.publishParcelEvent(event);
        return ResponseEntity.ok(new ApiResponse<>("ParcelEvent published for tracking number: " + event.trackingNumber()));
    }
}
