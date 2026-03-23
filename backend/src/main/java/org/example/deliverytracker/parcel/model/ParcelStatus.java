package org.example.deliverytracker.parcel.model;

public enum ParcelStatus {
    CREATED("Parcel Created"),
    RECEIVED("Parcel Received"),
    IN_TRANSIT("In Transit"),
    OUT_FOR_DELIVERY("Out for delivery"),
    DELIVERED("Delivered");

    private final String label;

    ParcelStatus(String label) {
        this.label = label;
    }

    public String getLabel() {
        return this.label;
    }
}
