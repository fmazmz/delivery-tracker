package org.example.deliverytracker.parcel.dto;


import java.math.BigDecimal;

public record CreateParcelDto(
        String trackingNumber,
        String location,
        BigDecimal weight
) {
}
