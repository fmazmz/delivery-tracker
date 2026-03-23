package org.example.deliverytracker.parcel;

import org.example.deliverytracker.parcel.dto.CreateParcelDto;
import org.example.deliverytracker.parcel.dto.ParcelDto;
import org.example.deliverytracker.parcel.model.Parcel;
import org.example.deliverytracker.parcel.model.WeightUnit;
import org.springframework.stereotype.Component;

@Component
public class ParcelMapper {
    public ParcelDto toDto(Parcel parcel) {
        return new ParcelDto(
                parcel.getId(),
                parcel.getTrackingNumber(),
                parcel.getStatus(),
                parcel.getStatus().getLabel(),
                parcel.getLocation(),
                parcel.getWeight(),
                WeightUnit.KG.getUnit(),
                parcel.getCreatedAt().toEpochMilli(),
                parcel.getUpdatedAt().toEpochMilli()
        );
    }
    public Parcel fromDto(CreateParcelDto dto) {
        return new Parcel(
                dto.trackingNumber(),
                dto.location(),
                dto.weight()
        );
    }
}
