package org.example.deliverytracker.parcel;

import org.example.deliverytracker.parcel.dto.CreateParcelDto;
import org.example.deliverytracker.parcel.dto.ParcelDto;
import org.example.deliverytracker.parcel.model.Parcel;
import org.springframework.stereotype.Component;

@Component
public class ParcelMapper {
    static ParcelDto toDto(Parcel parcel) {
        return new ParcelDto(
                parcel.getId(),
                parcel.getTrackingNumber(),
                parcel.getStatus(),
                parcel.getLocation(),
                parcel.getCreatedAt().toEpochMilli(),
                parcel.getUpdatedAt().toEpochMilli()
        );
    }
    static Parcel fromDto(CreateParcelDto dto) {
        return new Parcel(
                dto.trackingNumber(),
                dto.location()
        );
    }
}
