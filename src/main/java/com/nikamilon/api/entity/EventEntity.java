package com.nikamilon.api.entity;

import com.nikamilon.api.model.dictionary.EventStatus;
import com.nikamilon.api.model.dictionary.EventType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity @Table(name = "events", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"event_id", "event_name", "date_event"})
})
@NoArgsConstructor @AllArgsConstructor
@Builder @Data
@EntityListeners(AuditingEntityListener.class)
public class EventEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "event_id_gen")
    @SequenceGenerator(name = "event_id_gen", sequenceName = "event_id_seq")
    @Column(name = "event_id")
    private Long id;

    @Column(name = "event_name", nullable = false, length = 100)
    private String name;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity ownerId;

    @Column(name = "max_places", nullable = false)
    @ColumnDefault("0")
    private Long maxPlaces;

    @Column(name = "occupied_places", nullable = false)
    private Long occupiedPlaces = 0L;

    @Column(name = "cost_events", nullable = false)
    @ColumnDefault("0")
    private BigDecimal cost;

    @Column(nullable = false)
    private Long duration;

    @Enumerated(EnumType.STRING)
    @Column(name = "event_type")
    private EventType type;

    @Enumerated(EnumType.STRING)
    @Column(name = "event_status")
    private EventStatus status;

    @Column(name = "start_event")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dateStartEvent;

    @Column(name = "end_event")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dateEndEvent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")
    private LocationEntity location;

    @CreatedDate
    @Column(name = "date_created", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dateCreated;

    @Column(name = "date_updated", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private LocalDateTime dateUpdate;
}
