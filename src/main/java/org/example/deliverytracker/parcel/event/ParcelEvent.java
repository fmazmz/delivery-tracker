package org.example.deliverytracker.parcel.event;

import org.example.deliverytracker.parcel.model.ParcelStatus;

public record ParcelEvent(
    String trackingNumber,
    ParcelStatus status,
    String location
) {}
