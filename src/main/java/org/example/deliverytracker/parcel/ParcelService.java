package org.example.deliverytracker.parcel;

import jakarta.transaction.Transactional;
import org.example.deliverytracker.parcel.dto.CreateParcelDto;
import org.example.deliverytracker.parcel.dto.ParcelDto;
import org.example.deliverytracker.parcel.model.Parcel;
import org.example.deliverytracker.parcel.model.ParcelStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class ParcelService {

    private static final Logger logger = LoggerFactory.getLogger(ParcelService.class);

    private final ParcelRepository repository;
    private final ParcelMapper mapper;

    public ParcelService(ParcelRepository repository, ParcelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public ParcelDto create(CreateParcelDto dto) {
        if (repository.existsByTrackingNumber(dto.trackingNumber())) {
            throw new IllegalArgumentException("A parcel with the given tracking number is already created");
        }
        Parcel parcel = mapper.fromDto(dto);
        parcel.addTrackingEvent(dto.location(), ParcelStatus.CREATED, "Parcel created");
        Parcel saved = repository.saveAndFlush(parcel);

        return mapper.toDto(saved);
    }

    public void updateStatus(String trackingNumber, ParcelStatus status, String location) {
        updateStatus(trackingNumber, status, location, getDefaultDescription(status));
    }

    public void updateStatus(String trackingNumber, ParcelStatus status, String location, String description) {
        Parcel parcel = repository.findByTrackingNumber(trackingNumber)
                .orElseGet(() -> {
                    logger.info("Creating new parcel for tracking number: {}", trackingNumber);
                    Parcel newParcel = new Parcel(trackingNumber, location);
                    return newParcel;
                });

        parcel.addTrackingEvent(location, status, description);
        repository.save(parcel);

        logger.info("Updated parcel {} - Status: {}, Location: {}", trackingNumber, status, location);
    }

    public Optional<ParcelDto> findByTrackingNumber(String trackingNumber) {
        return repository.findByTrackingNumber(trackingNumber)
                .map(ParcelMapper::toDto);
    }

    private String getDefaultDescription(ParcelStatus status) {
        return switch (status) {
            case CREATED -> "Parcel created";
            case IN_TRANSIT -> "Parcel in transit";
            case DELIVERED -> "Parcel delivered";
        };
    }
}
