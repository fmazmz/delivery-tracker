package org.example.deliverytracker.parcel.dto;

import org.example.deliverytracker.parcel.model.Step;
import org.example.deliverytracker.parcel.model.enums.DeliveryType;
import org.example.deliverytracker.parcel.model.enums.ParcelStatus;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record ParcelDto(
        UUID id,
        String trackingNumber,
        ParcelStatus status,
        String statusLabel,
        String location,
        BigDecimal weight,
        String weightUnit,
        DeliveryType deliveryType,
        List<Step> steps,
        long createdAt,
        long updatedAt
) {
}
