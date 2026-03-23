package org.example.deliverytracker.parcel.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.deliverytracker.parcel.model.enums.DeliveryType;
import org.example.deliverytracker.parcel.model.enums.ParcelStatus;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Parcel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true)
    private String trackingNumber;

    @Enumerated(value = EnumType.STRING)
    private ParcelStatus status;

    private String location;

    private BigDecimal weight;

    @Enumerated(value = EnumType.STRING)
    private DeliveryType deliveryType;

    @CreationTimestamp
    private Instant createdAt;
    @UpdateTimestamp
    private Instant updatedAt;

    public Parcel(String trackingNumber, String location, BigDecimal weight, DeliveryType deliveryType) {
        this.trackingNumber = trackingNumber;
        this.location = location;
        this.weight = weight;
        this.deliveryType = deliveryType;
    }
}
