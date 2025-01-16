package com.nikamilon.api.service;

import com.nikamilon.api.response.LocationResponse;
import com.nikamilon.api.dto.LocationDto;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface LocationService {
    List<LocationResponse> getAllLocations();

    LocationResponse getLocationById(@Valid UUID locationId);

    LocationResponse createLocation(@Valid LocationDto inputDto);

    LocationResponse deleteById(@Valid UUID locationId);

    LocationResponse updateLocationById(UUID locationId, @Valid LocationDto inputData);
}
