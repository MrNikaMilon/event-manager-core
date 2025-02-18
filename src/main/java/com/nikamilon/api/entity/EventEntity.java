package com.nikamilon.api.entity;

import com.nikamilon.api.model.dictionary.EventType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@NoArgsConstructor @AllArgsConstructor
@Builder
@Table(name = "events")
public class EventEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "event_id_gen")
    @SequenceGenerator(name = "event_id_gen", sequenceName = "event_id_seq")
    @Column(name = "event_id")
    private Long id;

    @Size(max = 500)
    @Column(nullable = false, length = 500)
    private String description;

    @Size(max = 100)
    @Column(name = "event_name", nullable = false, length = 100)
    private String name;

    @Column(name = "date_start")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dateStart;

    @Column(name = "date_end", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dateEnd;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "loacation_id")
    private LocationEntity location;

    @Size(max = 20)
    @Enumerated(EnumType.STRING)
    @Column(name = "event_type")
    private EventType type;

    @ColumnDefault("now()")
    @Column(name = "date_created", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dateCreated;

    @Column(name = "date_updated", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dateUpdate;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity userCreated;

    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinTable(
            name = "users_by_event",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<UserEntity> usersByEvent = new ArrayList<>();
}
