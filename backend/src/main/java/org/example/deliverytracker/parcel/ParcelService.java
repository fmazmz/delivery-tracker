package org.example.deliverytracker.parcel;

import jakarta.transaction.Transactional;
import org.example.deliverytracker.parcel.dto.CreateParcelDto;
import org.example.deliverytracker.parcel.dto.ParcelDto;
import org.example.deliverytracker.parcel.event.ParcelEvent;
import org.example.deliverytracker.parcel.event.ParcelEventPublisher;
import org.example.deliverytracker.parcel.model.Parcel;
import org.example.deliverytracker.parcel.model.enums.ParcelStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ParcelService {

    private static final Logger logger = LoggerFactory.getLogger(ParcelService.class);

    private final ParcelRepository repository;
    private final ParcelMapper mapper;
    private final ParcelEventPublisher eventPublisher;

    public ParcelService(ParcelRepository repository, ParcelMapper mapper, ParcelEventPublisher eventPublisher) {
        this.repository = repository;
        this.mapper = mapper;
        this.eventPublisher = eventPublisher;
    }

    public ParcelDto createParcel(CreateParcelDto dto) {
        if (repository.existsByTrackingNumber(dto.trackingNumber())) {
            throw new IllegalArgumentException("A parcel with the given tracking number is already created");
        }
        Parcel parcel = mapper.fromDto(dto);
        parcel.setStatus(ParcelStatus.CREATED);
        Parcel saved = repository.saveAndFlush(parcel);

        eventPublisher.publishParcelEvent(
                new ParcelEvent(
                        saved.getTrackingNumber(),
                        saved.getStatus(),
                        saved.getLocation(),
                        saved.getWeight(),
                        saved.getDeliveryType())
        );

        return mapper.toDto(saved);
    }

    public void updateStatus(ParcelEvent event) {
        Parcel parcel = repository.findByTrackingNumber(event.trackingNumber())
                .orElseGet(() -> {
                    logger.info("Creating new parcel for tracking number: {}", event.trackingNumber());
                    Parcel newParcel = new Parcel(
                            event.trackingNumber(),
                            event.location(),
                            event.weight(),
                            event.deliveryType()
                    );

                    newParcel.setStatus(event.status());
                    return newParcel;
                });

        parcel.setStatus(event.status());
        parcel.setLocation(event.location());
        repository.save(parcel);

        logger.info("Updated parcel {} - Status: {}, Location: {}",
                event.trackingNumber(), event.status(), event.location());
    }

    public ParcelDto getParcelByTrackingNumber(String trackingNumber) {
        return repository.findByTrackingNumber(trackingNumber)
                .map(mapper::toDto)
                .orElseThrow(() ->
                        new IllegalArgumentException("Parcel not found with trackingNumber: " + trackingNumber)
                );
    }
}
