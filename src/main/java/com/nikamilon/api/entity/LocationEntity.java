package com.nikamilon.api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "locations")
@Data
@NoArgsConstructor @AllArgsConstructor
@Builder
public class LocationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "location_id_gen")
    @SequenceGenerator(name = "location_id_gen", sequenceName = "location_id_seq")
    @Column(name = "location_id")
    private Long id;

    @Size(max = 100)
    @Column(name = "address", nullable = false)
    private String address;

    @ColumnDefault("0")
    @Column(name = "address", nullable = false)
    private Long capacity;

    @Size(max = 200)
    @Column(name = "address", nullable = false)
    private String description;

    @Column(name = "date_created", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @ColumnDefault("now()")
    private LocalDateTime dateCreated;

    @Column(name = "date_updated", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dateUpdated;

    @Size(max = 200)
    @Column(name = "location_details")
    private String details;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "event_by_location",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "location_id")
    )
    private List<EventEntity> events;
}