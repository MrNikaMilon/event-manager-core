package com.nikamilon.api.service.impl;

import com.nikamilon.api.dto.dtos.LocationDTO;
import com.nikamilon.api.entity.LocationEntity;
import com.nikamilon.api.entity.UserEntity;
import com.nikamilon.api.handler.exception.LocationNotFoundException;
import com.nikamilon.api.mappers.LocationMapper;
import com.nikamilon.api.model.dictionary.UserRole;
import com.nikamilon.api.repository.LocationRepository;
import com.nikamilon.api.repository.UserRepository;
import com.nikamilon.api.dto.response.LocationResponse;
import com.nikamilon.api.service.LocationService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepository;
    private final UserRepository userRepository;

    private LocationMapper locationMapper;


    @Override
    @Transactional
    public List<LocationResponse> getAllLocations() {
        var locations = locationRepository.findAll();
        log.info("Successful find all locations, {}", locations);
        return locations.stream()
                .map(entity -> locationMapper.locationEntityToResponse(entity))
                .toList();
    }

    @Override
    @Transactional
    public LocationResponse getLocationById(Long locationId) {
        var locationEntity = locationRepository.findById(locationId);
        log.info("Find location, {}, by id {}", locationEntity.get(),  locationId);
        return locationMapper
                .locationEntityToResponse(locationEntity.get());
    }

    @Override
    @Transactional
    public LocationResponse createLocation(LocationDTO inputDto) {
        log.info("Create location, {}", inputDto);
        var savedEntity = locationMapper.locationDTOToEntity(inputDto);

        var savedUser = UserEntity.builder()
                .name("123")
                .email("123")
                .password("123")
                .build();
        userRepository.save(savedUser);

        savedEntity.setUserCreator(savedUser);
        log.info("Successful create location from dto, {}", inputDto);

        var responseFromDB = locationRepository.save(savedEntity);

        return locationMapper.locationEntityToResponse(responseFromDB);
    }

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
        log.info("Successful update location, {}", findLocation);
        return findLocation;
    }
}
