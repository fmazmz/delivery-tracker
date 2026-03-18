package org.example.deliverytracker.parcel.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
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

    @OneToMany(mappedBy = "parcel", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("timestamp DESC")
    private List<TrackingEvent> trackingHistory = new ArrayList<>();

    @CreationTimestamp
    private Instant createdAt;

    @UpdateTimestamp
    private Instant updatedAt;

    public Parcel(String trackingNumber, String location) {
        this.trackingNumber = trackingNumber;
        this.location = location;
    }

    public void addTrackingEvent(String location, ParcelStatus status, String description) {
        TrackingEvent event = new TrackingEvent(this, location, status, description);
        this.trackingHistory.add(event);
        this.location = location;
        this.status = status;
    }
}
