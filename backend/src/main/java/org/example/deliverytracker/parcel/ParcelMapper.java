package org.example.deliverytracker.parcel;

import org.example.deliverytracker.parcel.dto.CreateParcelDto;
import org.example.deliverytracker.parcel.dto.ParcelDto;
import org.example.deliverytracker.parcel.model.DeliveryFlow;
import org.example.deliverytracker.parcel.model.Parcel;
import org.example.deliverytracker.parcel.model.enums.WeightUnit;
import org.springframework.stereotype.Component;

@Component
public class ParcelMapper {
    private final DeliveryFlowFactory flowFactory;

    public ParcelMapper(DeliveryFlowFactory flowFactory) {
        this.flowFactory = flowFactory;
    }

    public ParcelDto toDto(Parcel parcel) {
        DeliveryFlow flow = flowFactory.getFlow(parcel.getDeliveryType());

        return new ParcelDto(
                parcel.getId(),
                parcel.getTrackingNumber(),
                parcel.getStatus(),
                parcel.getStatus().getLabel(),
                parcel.getLocation(),
                parcel.getWeight(),
                WeightUnit.KG.getUnit(),
                parcel.getDeliveryType(),
                flow.getSteps(),
                parcel.getCreatedAt().toEpochMilli(),
                parcel.getUpdatedAt().toEpochMilli()
        );
    }
    public Parcel fromDto(CreateParcelDto dto) {
        return new Parcel(
                dto.trackingNumber(),
                dto.location(),
                dto.weight(),
                dto.deliveryType()
        );
    }
}
