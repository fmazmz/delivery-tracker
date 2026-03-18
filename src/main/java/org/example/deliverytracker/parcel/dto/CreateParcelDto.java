package org.example.deliverytracker.parcel.dto;


public record CreateParcelDto(
        String trackingNumber,
        String location
) {
}
