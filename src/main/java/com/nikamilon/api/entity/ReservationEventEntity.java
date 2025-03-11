package com.nikamilon.api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "reservations_events", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "reservation_event_id", "event_reservation_id"
        })
})
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationEventEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "reservation_id_gen", sequenceName = "reservation_id_seq")
    @Column(name = "reservation_event_id", nullable = false)
    Long reservationEventId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_reservation_id", referencedColumnName = "event_id", nullable = false)
    EventEntity event;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_reservation_id", referencedColumnName = "location_id", nullable = false)
    LocationEntity location;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_by_event",
            joinColumns = @JoinColumn(referencedColumnName = "reservation_event_id"),
            inverseJoinColumns = @JoinColumn(referencedColumnName = "user_Id")
    )
    List<UserEntity> usersByEvents;
}
