package com.nikamilon.api.repository;

import com.nikamilon.api.dto.request.EventSearchRequest;
import com.nikamilon.api.entity.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<EventEntity, Long> {
    @Query(value = """
                select e from EventEntity e
                    where 1=1
                        and e.maxPlaces between :#{#searchDTO.placesMin()} and :#{#searchDTO.placesMax()}
                        and e.dateStartEvent >= :#{#searchDTO.dateStartAfter()}
                        and e.dateEndEvent <= :#{#searchDTO.dateStartBefore()}
                        and e.cost between :#{#searchDTO.costMin()} and :#{#searchDTO.costMax()}
                        and e.duration between :#{#searchDTO.durationMin()} and :#{#searchDTO.durationMax()}
                        and e.location.id = :#{#searchDTO.locationId()}
                        and e.status in :#{#searchDTO.eventStatus()}
            """)
    List<EventEntity> findBySearchParams(@Param("searchDTO") EventSearchRequest searchDTO);
}
