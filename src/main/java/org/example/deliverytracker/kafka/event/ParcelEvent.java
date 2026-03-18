package org.example.deliverytracker.kafka.event;

import org.example.deliverytracker.parcel.model.ParcelStatus;

public record ParcelEvent(
    String trackingNumber,
    ParcelStatus status,
    String location
) {}
