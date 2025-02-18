package com.nikamilon.api.controller;

import com.nikamilon.api.dto.EventDTO;
import com.nikamilon.api.dto.request.EventSearchRequest;
import com.nikamilon.api.response.EventResponse;
import com.nikamilon.api.service.EventService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    //base endpoint
    @GetMapping("/events/{event_id}")
    public ResponseEntity<EventResponse> getEvent(
            @Valid @PathVariable long eventId) {
        log.info("Successful return event by id: {}", eventId);
        return new ResponseEntity<>(
                eventService.getEventById(eventId),
                HttpStatus.OK
        );
    }

    @GetMapping("/events/search")
    public ResponseEntity<List<EventResponse>> getEvents(
            @Valid @RequestBody EventSearchRequest eventSearchRequest
    ){
        log.info("Successful return events by request: {}", eventSearchRequest);
        return new ResponseEntity<>(
                eventService.searchEvents(eventSearchRequest),
                HttpStatus.OK
        );
    }

    @PostMapping("/events")
    public ResponseEntity<EventResponse> createEvent(
            @Valid @RequestBody EventDTO eventDTO
    ){
        return new ResponseEntity<>(
                eventService.createNewEvent(eventDTO),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/events/{event_id}")
    public ResponseEntity<EventResponse> updateEvent(
            @PathVariable long evenId,
            @RequestBody EventDTO eventDTO
    ){
        return new ResponseEntity<>(
                eventService.updateEvent(evenId, eventDTO),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/events/{event_id}")
    public ResponseEntity<String> deleteEvent(
            @PathVariable long evenId
    ){
        eventService.deleteEventById(evenId);
        return new ResponseEntity<>(
                "Successful delete event with id: evenId",
                HttpStatus.OK
        );
    }

    //advanced endpoint, implemented after added model and user model
    @GetMapping("/events/my")
    public ResponseEntity<List<EventResponse>> getMyEvents(
            @Valid String userName
    ){
        //would be implemented after added user model in app and security
        throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED, "Endpoint not implemented yet, fuck off!");
    }

    @GetMapping("/events/registrations/my")
    public ResponseEntity<List<EventResponse>> getMyRegistrations(
            @Valid String userName
    ){
        //would be implemented after added user model in app and security
        throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED, "Endpoint not implemented yet, fuck off!");
    }

    @PostMapping("/events/registrations/{event_id}")
    public ResponseEntity<EventResponse> createRegistration(
            @Valid String userName, @PathVariable long evenId
    ){
        //would be implemented after added user model in app and security
        throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED, "Endpoint not implemented yet, fuck off!");
    }

    @DeleteMapping("/events/registrations/cancel/{event_id}")
    public String cancelRegistrationOnEvent(
            @Valid String userName, @PathVariable long evenId
    ){
        //would be implemented after added user model in app and security
        throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED, "Endpoint not implemented yet, fuck off!");
    }
}
