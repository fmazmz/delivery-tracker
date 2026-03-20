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

    private static final Logger LOGGER = LoggerFactory.getLogger(ParcelService.class);

    private final ParcelRepository repository;
    private final ParcelMapper mapper;

    public ParcelService(ParcelRepository repository, ParcelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public ParcelDto createParcel(CreateParcelDto dto) {
        if (repository.existsByTrackingNumber(dto.trackingNumber())) {
            throw new IllegalArgumentException("A parcel with the given tracking number is already created");
        }
        Parcel parcel = mapper.fromDto(dto);
        parcel.addTrackingEvent(dto.location(), ParcelStatus.CREATED, "Parcel created");
        Parcel saved = repository.saveAndFlush(parcel);

        return mapper.toDto(saved);
    }


    public void updateParcelStatus(String trackingNumber, ParcelStatus status, String location) {
        Parcel parcel = repository.findByTrackingNumber(trackingNumber)
                .orElseGet(() -> {
                    LOGGER.info("Creating new parcel for tracking number: {}", trackingNumber);
                    Parcel newParcel = new Parcel(trackingNumber, location);
                    return newParcel;
                });

        parcel.addTrackingEvent(location, status, ParcelStatus.getDescription(status));
        repository.save(parcel);

        LOGGER.info("Updated parcel {} - Status: {}, Location: {}", trackingNumber, status, location);
    }

    public Optional<ParcelDto> findByTrackingNumber(String trackingNumber) {
        return repository.findByTrackingNumber(trackingNumber)
                .map(ParcelMapper::toDto);
    }
}
