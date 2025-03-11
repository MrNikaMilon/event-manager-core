package com.nikamilon.api.mappers;


import com.nikamilon.api.dto.dtos.LocationDTO;
import com.nikamilon.api.entity.LocationEntity;
import com.nikamilon.api.message.SuccessMessageExtension;
import com.nikamilon.api.dto.response.LocationResponse;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDateTime;

@Log4j2
@ExtendWith(SuccessMessageExtension.class)
class LocationMapperTest {

    private final LocationMapper mapper = Mappers.getMapper(LocationMapper.class);

    private LocationEntity getTestLocationEntity() {
        return LocationEntity.builder()
                .id(1L)
                .nameLocation("Test Location")
                .address("123 Test Street")
                .capacity(100L)
                .description("Test location for events")
                .dateCreated(LocalDateTime.of(2025, 2, 4, 12, 1, 55, 904210000))
                .dateUpdated(LocalDateTime.of(2025, 2, 4, 12, 1, 55, 904210000))
                .build();
    }

    private LocationResponse getTestLocationResponse() {
        return LocationResponse.builder()
                .name("Test Location")
                .address("123 Test Street")
                .capacity(100L)
                .description("Test location for events")
                .build();
    }

    private LocationDTO getTestLocationDTO() {
        return LocationDTO.builder()
                .name("Test Location")
                .address("123 Test Street")
                .capacity(100L)
                .description("Test location for events")
                .build();
    }

    @AfterAll
    static void afterAllTests() {
        System.out.println("All tests passed");
    }

    @Test
    @DisplayName("Test mapping DTO to Entity")
    void testMappingToEntity(){
        var dto = getTestLocationDTO();

        var entity = mapper.locationDTOToEntity(dto);
        entity.setId(1L);
        entity.setDateCreated(LocalDateTime.of(2025, 2, 4, 12, 1, 55, 904210000));
        entity.setDateUpdated(LocalDateTime.of(2025, 2, 4, 12, 1, 55, 904210000));

        System.out.println("Mapping DTO %s".formatted(dto));
        System.out.println("Mapping Entity %s".formatted(entity));

        assertNotNull(entity);
        assertEquals(entity, getTestLocationEntity());
    }

    @Test
    @DisplayName("Test mapping Entity to Response")
    void testMappingToResponse(){
        var entity = getTestLocationEntity();

        var response = mapper.locationEntityToResponse(entity);

        assertNotNull(response);
        assertEquals(getTestLocationResponse(), response);
    }
}
