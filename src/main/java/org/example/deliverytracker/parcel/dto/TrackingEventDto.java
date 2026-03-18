package org.example.deliverytracker.parcel.dto;

import org.example.deliverytracker.parcel.model.ParcelStatus;

import java.util.UUID;

public record TrackingEventDto(
        UUID id,
        String location,
        ParcelStatus status,
        String description,
        long timestamp
) {
}
