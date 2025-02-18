package com.nikamilon.api.service.impl;

import com.nikamilon.api.dto.LocationDTO;
import com.nikamilon.api.entity.EventEntity;
import com.nikamilon.api.entity.LocationEntity;
import com.nikamilon.api.exception.LocationNotFoundException;
import com.nikamilon.api.mappers.EventMapper;
import com.nikamilon.api.mappers.LocationMapper;
import com.nikamilon.api.repository.LocationRepository;
import com.nikamilon.api.response.LocationResponse;
import com.nikamilon.api.service.LocationService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepository;
    private LocationMapper locationMapper;
    private EventMapper eventMapper;

    /**
     * @return returned lust {@link LocationResponse} to another system or frontend
     */
    @Override
    @Transactional
    public List<LocationResponse> getAllLocations() {
        var locations = locationRepository.findAll();
        log.info("Successful find all locations, {}", locations);
        return locations.stream()
                .map(entity -> locationMapper.locationEntityToResponse(entity))
                .toList();
    }

    /**
     * @param locationId its location id for search in DB
     * @return returned response {@link LocationResponse} to another system or frontend
     */
    @Override
    @Transactional
    public LocationResponse getLocationById(Long locationId) {
        var locationEntity = locationRepository.findById(locationId);
        log.info("Find location, {}, by id {}", locationEntity.get(),  locationId);
        return locationMapper
                .locationEntityToResponse(locationEntity.get());
    }

    /**
     * @param inputDto {@link LocationDTO} from another system or frontend
     * @return returned response {@link LocationResponse} to another system or frontend
     */
    @Override
    @Transactional
    public LocationResponse createLocation(LocationDTO inputDto) {
        var savedEntity = locationMapper.locationDTOToEntity(inputDto);
        log.info("Successful create location from dto, {}", inputDto);
        var responseFromDB = locationRepository.save(savedEntity);
        return locationMapper.locationEntityToResponse(responseFromDB);
    }

    /**
     * @param locationId location id for delete location in DB
     */
    @Override
    @Transactional
    public void deleteById(Long locationId) {
        var existEntity = locationRepository.findById(locationId);
        if (existEntity.isPresent()) {
            locationRepository.delete(existEntity.get());
            log.info("Deleted location by id, {}", locationId);
        } else {
            throw new EntityNotFoundException("Location with id " + locationId + " not found");
        }
    }

    /**
     * @param locationId id for search existing location in DB
     * @param locationDTO new data {@link LocationDTO} from frontend or another service
     * @return returned response {@link LocationResponse} to frontend or another service
     * @throws LocationNotFoundException exception if requested entity not found in DB
     */
    @Override
    @Transactional
    public LocationResponse updateLocationById(Long locationId, LocationDTO locationDTO) throws LocationNotFoundException {
        var findLocation = locationRepository.findById(locationId)
                .orElseThrow(() -> new LocationNotFoundException("Location not found"));
        log.info("Successful search location, by id {}", locationId);

        var updatedLocation = updateEntityFields(findLocation, locationDTO);
        var savedEntity = locationRepository.save(updatedLocation);

        log.info("Successful update event from dto, {}", locationDTO);
        return locationMapper.locationEntityToResponse(savedEntity);
    }

    /**
     * @param findLocation fined location {@link LocationEntity} from DB
     * @param newData new data {@link LocationDTO} from frontend and another system
     * @return returned updated entity {@link LocationEntity}
     */
    private LocationEntity updateEntityFields(LocationEntity findLocation, LocationDTO newData) {
        findLocation.setAddress(newData.address());
        findLocation.setCapacity(newData.capacity());
        findLocation.setDescription(newData.description());
        findLocation.setDateUpdated(LocalDateTime.now());
        findLocation.setDetails(newData.details());
        List<EventEntity> updatedEntity = new ArrayList<>();
        newData.eventsId().forEach(eventId -> {
            updatedEntity.add(eventMapper.eventDTOToEntity(eventId));
        });
        findLocation.setEvents(updatedEntity);
        log.info("Successful update location, {}", findLocation);
        return findLocation;
    }
}
