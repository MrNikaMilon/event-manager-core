package com.nikamilon.api.service.impl;

import com.nikamilon.api.dto.LocationDto;
import com.nikamilon.api.response.LocationResponse;
import com.nikamilon.api.service.LocationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class LocationServiceImpl implements LocationService {

    @Override
    public List<LocationResponse> getAllLocations() {
        return List.of();
    }

    @Override
    public LocationResponse getLocationById(UUID locationId) {
        return null;
    }

    @Override
    public LocationResponse createLocation(LocationDto inputDto) {
        return null;
    }

    @Override
    public LocationResponse deleteById(UUID locationId) {
        return null;
    }

    @Override
    public LocationResponse updateLocationById(UUID locationId, LocationDto inputData) {
        return null;
    }
}
