package com.nikamilon.api.repository;

import com.nikamilon.api.domain.entity.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EvenetRepository extends JpaRepository<EventEntity, UUID> {
}
