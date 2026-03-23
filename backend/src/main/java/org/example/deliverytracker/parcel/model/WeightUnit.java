package org.example.deliverytracker.parcel.model;

public enum WeightUnit {
    KG("Kg");

    private final String unit;

    WeightUnit(String unit) {
        this.unit = unit;
    }

    public String getUnit() {
        return this.unit;
    }
}
