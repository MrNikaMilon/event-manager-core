package com.nikamilon.api.mappers;

import com.nikamilon.api.dto.request.EventCreateRequestDTO;
import com.nikamilon.api.dto.request.EventUpdateRequestDTO;
import com.nikamilon.api.entity.EventEntity;
import com.nikamilon.api.entity.UserEntity;
import com.nikamilon.api.message.SuccessMessageExtension;
import com.nikamilon.api.model.dictionary.EventStatus;
import com.nikamilon.api.model.dictionary.EventType;
import com.nikamilon.api.model.dictionary.UserRole;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;


@Log4j2
@ExtendWith(SuccessMessageExtension.class)
public class EventMapperTest {

    private EventMapper eventMapper = Mappers.getMapper(EventMapper.class);

    private UserEntity getUserEntity() {
        return UserEntity.builder()
                .name("John Doe")
                .email("john.doe@example.com")
                .role(UserRole.ADMIN)
                .password("password")
                .build();
    }

    private EventEntity getEventEntity(){
        return EventEntity.builder()
                .id(1L)
                .name("Test festival")
                .ownerId(getUserEntity())
                .maxPlaces(20L)
                .occupiedPlaces(0L)
                .dateEvent(LocalDateTime.of(2025, 2, 4, 12, 1, 55, 904210000))
                .cost(BigDecimal.valueOf(100))
                .duration(30L)
                .type(EventType.FESTIVAL)
                .status(EventStatus.WAIT_START)
                .dateCreated(LocalDateTime.of(2025, 2, 4, 12, 1, 55, 904210000))
                .dateUpdate(LocalDateTime.of(2025, 2, 4, 12, 1, 55, 904210000))
                .build();
    }

    private EventCreateRequestDTO getEventCreateRequestDTO(){
        return EventCreateRequestDTO.builder()
                .name("Test festival")
                .maxPlaces(20)
                .duration(30)
                .date(LocalDateTime.of(2025, 2, 4, 12, 1, 55, 904210000))
                .cost(BigDecimal.valueOf(100))
                .eventType("Festival")
                .build();
    }

    private EventUpdateRequestDTO getEventUpdateRequestDTO(){
        return EventUpdateRequestDTO.builder()
                .name("Test festival")
                .maxPlaces(20)
                .date(LocalDateTime.of(2025, 2, 4, 12, 1, 55, 904210000))
                .cost(BigDecimal.valueOf(100))
                .eventType("Festival")
                .build();
    }

    @Test
    public void testEventCreateRequestToEntity() {
        var createDto = getEventCreateRequestDTO();

        var entity = eventMapper.eventCreationDTOToEntity(createDto);
        entity.setId(1L);
        entity.setDateCreated(
                LocalDateTime.of(2025, 2, 4, 12, 1, 55, 904210000));
        entity.setDateUpdate(
                LocalDateTime.of(2025, 2, 4, 12, 1, 55, 904210000));
        entity.setOwnerId(getUserEntity());
        entity.setOccupiedPlaces(0L);
        entity.setStatus(EventStatus.WAIT_START);
        entity.setDateEvent(
                LocalDateTime.of(2025, 2, 4, 12, 1, 55, 904210000));

        assertNotNull(entity);
        assertEquals(entity, getEventEntity());
    }

    @Test
    public void testEventUpdateRequestToEntity() {

    }

    @Test
    public void testEventDTOToEntity() {

    }
}
