package org.example.deliverytracker.parcel.dto;

import org.example.deliverytracker.parcel.model.ParcelStatus;

import java.math.BigDecimal;
import java.util.UUID;

public record ParcelDto(
        UUID id,
        String trackingNumber,
        ParcelStatus status,
        String statusLabel,
        String location,
        BigDecimal weight,
        String weightUnit,
        long createdAt,
        long updatedAt
) {
}
