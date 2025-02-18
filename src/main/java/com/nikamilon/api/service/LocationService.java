package com.nikamilon.api.service;

import com.nikamilon.api.exception.LocationNotFoundException;
import com.nikamilon.api.response.LocationResponse;
import com.nikamilon.api.dto.LocationDTO;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LocationService {
    List<LocationResponse> getAllLocations();

    LocationResponse getLocationById(@Valid Long locationId);

    LocationResponse createLocation(@Valid LocationDTO inputDto);

    void deleteById(@Valid Long locationId);

    LocationResponse updateLocationById(Long locationId, @Valid LocationDTO inputData) throws LocationNotFoundException;
}
