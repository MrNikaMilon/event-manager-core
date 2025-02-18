package com.nikamilon.api.controller;

import com.nikamilon.api.exception.LocationNotFoundException;
import com.nikamilon.api.repository.LocationRepository;
import com.nikamilon.api.response.LocationResponse;
import com.nikamilon.api.dto.LocationDTO;
import com.nikamilon.api.service.LocationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class LocationController {

    private final LocationService locationService;

    @GetMapping("/locations")
    public ResponseEntity<List<LocationResponse>> getAllLocations(){
        log.info("Successful return all location with code, {}", HttpStatus.OK);
        return new ResponseEntity<>(
                locationService.getAllLocations(),
                HttpStatus.OK
        );
    }

    @GetMapping("/locations/{location_id}")
    public ResponseEntity<LocationResponse> getLocationById(
            @Valid @PathVariable("location_id") Long locationId
    ){
        log.info("Successful return location by id: {}, with code, {}", locationId, HttpStatus.OK);
        return new ResponseEntity<>(
                locationService.getLocationById(locationId),
                HttpStatus.OK
        );
    }

    @PostMapping("/locations")
    public ResponseEntity<LocationResponse> createLocation(
            @Valid @RequestBody LocationDTO inputDto
    ){
            log.info("Successful create location with resource, {}", inputDto);
            return new ResponseEntity<>(
                    locationService.createLocation(inputDto),
                    HttpStatus.OK
            );
        }

    @DeleteMapping("/locations/{location_id}")
    public ResponseEntity<String> deleteLocationById(
            @Valid @PathVariable("location_id") Long locationId
    ){
        locationService.deleteById(locationId);
        log.info("Successful delete location with id, {}", locationId);
        return new ResponseEntity<>(
                "Location with id: %s was deleted".formatted(locationId),
                HttpStatus.OK
        );
    }

    @PutMapping("/locations/{location_id}")
    public ResponseEntity<LocationResponse> updateLocationById(
            @PathVariable("location_id") Long locationId,
            @Valid  @RequestBody LocationDTO inputData
    ) throws LocationNotFoundException {
            log.info("Successful update location with id, {} and resource: {}", locationId, inputData);
            return new ResponseEntity<>(
                    locationService.updateLocationById(locationId, inputData),
                    HttpStatus.OK
            );
        }
}
