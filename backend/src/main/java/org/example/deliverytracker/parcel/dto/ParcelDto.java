package org.example.deliverytracker.parcel.dto;

import org.example.deliverytracker.parcel.model.ParcelStatus;

import java.util.UUID;

public record ParcelDto(
        UUID id,
        String trackingNumber,
        ParcelStatus status,
        String location,
        long createdAt,
        long updatedAt
) {
}
