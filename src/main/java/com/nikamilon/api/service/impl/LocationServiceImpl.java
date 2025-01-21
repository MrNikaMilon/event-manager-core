package com.nikamilon.api.service.impl;

import com.nikamilon.api.dto.LocationDto;
import com.nikamilon.api.exception.LocationNotFoundException;
import com.nikamilon.api.mapper.impl.EventMapperImpl;
import com.nikamilon.api.mapper.impl.LocationMapperImpl;
import com.nikamilon.api.repository.LocationRepository;
import com.nikamilon.api.response.LocationResponse;
import com.nikamilon.api.service.LocationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepository;

    private final LocationMapperImpl locationMapper;

    private final EventMapperImpl eventMapper;

    @Override
    @Transactional
    public List<LocationResponse> getAllLocations() {
        var locations = locationRepository.findAll();
        log.info("Successful find all locations, {}", locations);
        return locations.stream()
                .map(locationMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional
    public LocationResponse getLocationById(Long locationId) {
        var locationEntity = locationRepository.findById(locationId);
        log.info("Find location, {}, by id {}", locationEntity.get(),  locationId);
        return locationMapper
                .toResponse(locationEntity.get());
    }

    @Override
    @Transactional
    public LocationResponse createLocation(LocationDto inputDto) {
        var savedEntity = locationMapper.toEntity(inputDto);
        log.info("Successful create location from dto, {}", inputDto);

        var responseFromDB = locationRepository.save(savedEntity);
        return locationMapper.toResponse(responseFromDB);
    }

    @Override
    @Transactional
    public void deleteById(Long locationId) {
        locationRepository.deleteById(locationId);
        log.info("Delete location by id, {}", locationId);
    }

    @Override
    @Transactional
    public LocationResponse updateLocationById(Long locationId, LocationDto inputData) {
        var updatedLocation = locationRepository.findById(locationId)
                .orElseThrow(() -> new LocationNotFoundException("Location not found"));
        log.info("Successful search location, by id {}", locationId);

        updatedLocation.setAddress(inputData.address());
        updatedLocation.setCapacity(inputData.capacity());
        updatedLocation.setDescription(inputData.description());
        updatedLocation.setDateUpdated(LocalDateTime.now());
        updatedLocation.setDetails(inputData.details());
        var eventEntity =  inputData.events()
                .stream()
                .map(eventMapper::toEntity)
                .toList();
        updatedLocation.setEvents(eventEntity);

        var savedEntity = locationRepository.save(updatedLocation);

        return locationMapper.toResponse(savedEntity);
    }
}
