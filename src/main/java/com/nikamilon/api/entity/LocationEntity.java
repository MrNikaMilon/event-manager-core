package com.nikamilon.api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "locations", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "location_id", "location_name", "address"
        })
})
@Data @Builder
@NoArgsConstructor @AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class LocationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "location_id_gen")
    @SequenceGenerator(name = "location_id_gen", sequenceName = "location_id_seq")
    @Column(name = "location_id")
    private Long id;

    @Column(name = "location_name", scale = 100, nullable = false)
    private String nameLocation;

    @Column(name = "address", scale = 100, nullable = false)
    private String address;

    @ColumnDefault("0")
    @Column(name = "capacity", nullable = false)
    private Long capacity;

    @Column(name = "description", scale = 200, nullable = false)
    private String description;

    @Column(name = "date_created", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @CreatedDate
    private LocalDateTime dateCreated;

    @Column(name = "date_updated")
    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    @LastModifiedBy
    private LocalDateTime dateUpdated;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_creator_id", referencedColumnName = "user_id", nullable = false)
    private UserEntity userCreator;
}