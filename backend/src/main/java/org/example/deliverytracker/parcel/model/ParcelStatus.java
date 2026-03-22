package org.example.deliverytracker.parcel.model;

public enum ParcelStatus {
    CREATED,
    IN_TRANSIT,
    DELIVERED;

    public static String getDescription(ParcelStatus status) {
        return switch (status) {
            case CREATED -> "Parcel Created";
            case IN_TRANSIT -> "In transit";
            case DELIVERED -> "Delivered";
        };
    }
}
