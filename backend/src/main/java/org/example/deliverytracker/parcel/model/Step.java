package org.example.deliverytracker.parcel.model;

import lombok.Getter;

@Getter
public class Step {
    private final int order;
    private final String name;

    public Step(int order, String name) {
        this.order = order;
        this.name = name;
    }
}
