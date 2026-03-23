package org.example.deliverytracker.parcel.model;

import org.example.deliverytracker.parcel.model.enums.DeliveryType;

import java.util.List;

public class StandardDeliveryFlow extends DeliveryFlow{
    public StandardDeliveryFlow() {
        super(
                DeliveryType.STANDARD,
                List.of(
                        new Step(1, "Created"),
                        new Step(2, "Picked Up"),
                        new Step(3, "Arrived at facility"),
                        new Step(4, "In Transit"),
                        new Step(5, "Arrived at local facility"),
                        new Step(6, "Out for delivery"),
                        new Step(7, "Delivered")
                )
        );
    }
}
