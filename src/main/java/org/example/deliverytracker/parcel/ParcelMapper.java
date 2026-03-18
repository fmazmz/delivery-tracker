package org.example.deliverytracker.parcel;

import org.example.deliverytracker.parcel.dto.CreateParcelDto;
import org.example.deliverytracker.parcel.dto.ParcelDto;
import org.example.deliverytracker.parcel.dto.TrackingEventDto;
import org.example.deliverytracker.parcel.model.Parcel;
import org.example.deliverytracker.parcel.model.TrackingEvent;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ParcelMapper {
    static ParcelDto toDto(Parcel parcel) {
        List<TrackingEventDto> trackingHistory = parcel.getTrackingHistory().stream()
                .map(ParcelMapper::toDto)
                .toList();

        return new ParcelDto(
                parcel.getId(),
                parcel.getTrackingNumber(),
                parcel.getStatus(),
                parcel.getLocation(),
                trackingHistory,
                parcel.getCreatedAt().toEpochMilli(),
                parcel.getUpdatedAt().toEpochMilli()
        );
    }

    static TrackingEventDto toDto(TrackingEvent event) {
        return new TrackingEventDto(
                event.getId(),
                event.getLocation(),
                event.getStatus(),
                event.getDescription(),
                event.getTimestamp().toEpochMilli()
        );
    }

    static Parcel fromDto(CreateParcelDto dto) {
        return new Parcel(
                dto.trackingNumber(),
                dto.location()
        );
    }
}
