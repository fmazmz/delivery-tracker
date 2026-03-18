package org.example.deliverytracker.parcel;

import org.example.deliverytracker.parcel.model.Parcel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ParcelRepository extends JpaRepository<Parcel, UUID> {
    boolean existsByTrackingNumber(String trackingNumber);
    Optional<Parcel> findByTrackingNumber(String trackingNumber);
}
