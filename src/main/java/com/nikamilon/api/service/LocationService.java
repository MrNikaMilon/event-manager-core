package com.nikamilon.api.service;

import com.nikamilon.api.response.LocationResponse;
import com.nikamilon.api.dto.LocationDto;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LocationService {
    List<LocationResponse> getAllLocations();

    LocationResponse getLocationById(@Valid Long locationId);

    LocationResponse createLocation(@Valid LocationDto inputDto);

    void deleteById(@Valid Long locationId);

    LocationResponse updateLocationById(Long locationId, @Valid LocationDto inputData);
}
