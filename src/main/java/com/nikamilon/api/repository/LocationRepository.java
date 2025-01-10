package com.nikamilon.api.repository;

import com.nikamilon.api.domain.entity.LocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;


import java.time.LocalDate;
import java.util.UUID;

public interface LocationRepository extends JpaRepository<LocationEntity, UUID> {
}
