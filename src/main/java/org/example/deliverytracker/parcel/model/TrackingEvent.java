package org.example.deliverytracker.parcel.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class TrackingEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parcel_id", nullable = false)
    private Parcel parcel;

    private String location;

    @Enumerated(EnumType.STRING)
    private ParcelStatus status;

    private String description;

    @CreationTimestamp
    private Instant timestamp;

    public TrackingEvent(Parcel parcel, String location, ParcelStatus status, String description) {
        this.parcel = parcel;
        this.location = location;
        this.status = status;
        this.description = description;
    }
}
