package com.nikamilon.api.mappers;


import com.nikamilon.api.dto.EventDTO;
import com.nikamilon.api.dto.LocationDTO;
import com.nikamilon.api.entity.EventEntity;
import com.nikamilon.api.entity.LocationEntity;
import com.nikamilon.api.message.SuccessMessageExtension;
import com.nikamilon.api.model.dictionary.EventType;
import com.nikamilon.api.response.EventResponse;
import com.nikamilon.api.response.LocationResponse;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDateTime;
import java.util.List;

@Log4j2
@ExtendWith(SuccessMessageExtension.class)
class LocationMapperTest {

    private final LocationMapper mapper = Mappers.getMapper(LocationMapper.class);

    private LocationEntity getTestLocationEntity() {
        return LocationEntity.builder()
                .id(1L)
                .address("123 Test Street")
                .capacity(100L)
                .description("Test location for events")
                .dateCreated(LocalDateTime.of(2025, 2, 4, 12, 1, 55, 904210000))
                .dateUpdated(LocalDateTime.of(2025, 2, 4, 12, 1, 55, 904210000))
                .details("Some additional location details")
                .events(null)
                .build();
    }

    private LocationResponse getTestLocationResponse() {
        return LocationResponse.builder()
                .address("123 Test Street")
                .capacity(100L)
                .description("Test location for events")
                .details("Some additional location details")
                .events(null)
                .build();
    }

    private LocationDTO getTestLocationDTO() {
        return LocationDTO.builder()
                .address("123 Test Street")
                .capacity(100L)
                .description("Test location for events")
                .details("Some additional location details")
                .eventsId(null)
                .build();
    }

    private LocationEntity getTestLocationEntityWithEvents(){
        return LocationEntity.builder()
                .id(1L)
                .address("123 Test Street")
                .capacity(100L)
                .description("Test location for events")
                .dateCreated(LocalDateTime.of(2025, 2, 4, 12, 1, 55, 904210000))
                .dateUpdated(LocalDateTime.of(2025, 2, 4, 12, 1, 55, 904210000))
                .details("Some additional location details")
                .events(List.of(
                        EventEntity.builder()
                                .description("Annual Music Festival with international artists.")
                                .name("Music Fest 2025")
                                .dateStart(LocalDateTime.of(2025, 6, 20, 15, 0, 0, 904210000))
                                .dateEnd(LocalDateTime.of(2025, 6, 20, 23, 0, 0, 904210000))
                                .type(EventType.getEventByString("Festival"))
                                .build(),
                        EventEntity.builder()
                                .description("A conference focusing on the latest innovations in technology.")
                                .name("Innovate 2025")
                                .dateStart(LocalDateTime.of(2025, 9, 10, 9, 0, 0, 904210000))
                                .dateEnd(LocalDateTime.of(2025, 9, 10, 17, 0, 0, 904210000))
                                .type(EventType.getEventByString("Conference"))
                                .build()
                ))
                .build();
    }

    private LocationResponse getTestLocationResponseWithEvents(){
        return LocationResponse.builder()
                .address("123 Test Street")
                .capacity(100L)
                .description("Test location for events")
                .details("Some additional location details")
                .events(List.of(
                        EventResponse.builder().build(),
                        EventResponse.builder().build()
                ))
                .build();
    }

    private LocationDTO getTestLocationDTOWithEvents(){
        return LocationDTO.builder()
                .address("123 Test Street")
                .capacity(100L)
                .description("Test location for events")
                .details("Some additional location details")
                .eventsId(List.of(
                        EventDTO.builder()
                                .description("Annual Music Festival with international artists.")
                                .name("Music Fest 2025")
                                .dateStart(LocalDateTime.of(2025, 6, 20, 15, 0, 0, 904210000))
                                .dateEnd(LocalDateTime.of(2025, 6, 20, 23, 0, 0, 904210000))
                                .eventType("Festival")
                                .build(),
                        EventDTO.builder()
                                .description("A conference focusing on the latest innovations in technology.")
                                .name("Innovate 2025")
                                .dateStart(LocalDateTime.of(2025, 9, 10, 9, 0, 0, 904210000))
                                .dateEnd(LocalDateTime.of(2025, 9, 10, 17, 0, 0, 904210000))
                                .eventType("Conference")
                                .build()
                ))
                .build();
    }

    @AfterAll
    static void afterAllTests() {
        System.out.println("All tests passed");
    }

    @Test
    @DisplayName("Test mapping DTO to entity")
    void testMappingToEntity(){
        var dto = getTestLocationDTO();

        var entity = mapper.locationDTOToEntity(dto);
        entity.setId(1L);
        entity.setDateCreated(LocalDateTime.of(2025, 2, 4, 12, 1, 55, 904210000));
        entity.setDateUpdated(LocalDateTime.of(2025, 2, 4, 12, 1, 55, 904210000));

        assertNotNull(entity);
        assertEquals(entity, getTestLocationEntity());
    }

    @Test
    @DisplayName("Test mapping entity to response")
    void testMappingToResponse(){
        var entity = getTestLocationEntity();

        var response = mapper.locationEntityToResponse(entity);
        response.events();

        assertNotNull(response);
        assertEquals(getTestLocationResponse(), response);

    }


    @Test
    @DisplayName("Test mapping DTO to entity with event")
    void testMappingToEntityWithEvent(){
        var entity = getTestLocationDTOWithEvents();
        log.error("Events: " + entity.eventsId());

        var response = mapper.locationDTOToEntity(entity);

        assertNotNull(response);
        assertEquals(getTestLocationResponseWithEvents(), response);
    }

    @Test
    @DisplayName("Test mapping entity to response with event")
    void testMappingToResponseWithEvent(){}

}
