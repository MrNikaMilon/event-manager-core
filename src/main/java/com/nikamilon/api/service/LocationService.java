package com.nikamilon.api.service;

import com.nikamilon.api.handler.exception.LocationNotFoundException;
import com.nikamilon.api.dto.response.LocationResponse;
import com.nikamilon.api.dto.dtos.LocationDTO;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LocationService {
    /**
     * @return returned lust {@link LocationResponse} to another system or frontend
     */
    List<LocationResponse> getAllLocations();

    /**
     * @param locationId its location id for search in DB
     * @return returned response {@link LocationResponse} to another system or frontend
     */
    LocationResponse getLocationById(@Valid Long locationId);

    /**
     * @param inputDto {@link LocationDTO} from another system or frontend
     * @return returned response {@link LocationResponse} to another system or frontend
     */
    LocationResponse createLocation(@Valid LocationDTO inputDto);

    /**
     * @param locationId location id for delete location in DB
     */
    void deleteById(@Valid Long locationId);

    /**
     * @param locationId id for search existing location in DB
     * @param inputData new data {@link LocationDTO} from frontend or another service
     * @return returned response {@link LocationResponse} to frontend or another service
     * @throws LocationNotFoundException exception if requested entity not found in DB
     */
    LocationResponse updateLocationById(Long locationId, LocationDTO inputData) throws LocationNotFoundException;
}
