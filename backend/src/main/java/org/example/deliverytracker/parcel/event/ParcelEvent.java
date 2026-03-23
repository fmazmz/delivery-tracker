package org.example.deliverytracker.parcel.event;

import org.example.deliverytracker.parcel.model.ParcelStatus;

import java.math.BigDecimal;

public record ParcelEvent(
    String trackingNumber,
    ParcelStatus status,
    String location,
    BigDecimal weight
) {}
