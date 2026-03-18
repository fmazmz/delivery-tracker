package org.example.deliverytracker.parcel;

import org.example.deliverytracker.parcel.model.TrackingEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TrackingEventRepository extends JpaRepository<TrackingEvent, UUID> {
    List<TrackingEvent> findByParcelIdOrderByTimestampDesc(UUID parcelId);
}
