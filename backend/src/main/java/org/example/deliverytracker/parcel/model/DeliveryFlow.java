package org.example.deliverytracker.parcel.model;

import lombok.Getter;
import org.example.deliverytracker.parcel.model.enums.DeliveryType;

import java.util.ArrayList;
import java.util.List;

public abstract class DeliveryFlow {
    private final DeliveryType type;
    @Getter
    private final List<Step> steps;

    public DeliveryFlow(DeliveryType type, List<Step> steps) {
        this.type = type;
        this.steps = new ArrayList<>(steps);
    }

}
