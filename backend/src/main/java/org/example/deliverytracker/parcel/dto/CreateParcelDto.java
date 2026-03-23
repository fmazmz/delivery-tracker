package org.example.deliverytracker.parcel.dto;


import org.example.deliverytracker.parcel.model.enums.DeliveryType;

import java.math.BigDecimal;

public record CreateParcelDto(
        String trackingNumber,
        String location,
        BigDecimal weight,
        DeliveryType deliveryType
) {
}
