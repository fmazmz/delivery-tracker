package org.example.deliverytracker.parcel;

import org.example.deliverytracker.parcel.model.DeliveryFlow;
import org.example.deliverytracker.parcel.model.StandardDeliveryFlow;
import org.example.deliverytracker.parcel.model.enums.DeliveryType;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class DeliveryFlowFactory {

    private final Map<DeliveryType, DeliveryFlow> flows;

    public DeliveryFlowFactory() {
        flows = Map.of(
            DeliveryType.STANDARD, new StandardDeliveryFlow()
        );
    }

    public DeliveryFlow getFlow(DeliveryType type) {
        return flows.get(type);
    }
}