package com.nikamilon.api.service;

import com.nikamilon.api.dto.EventDTO;
import com.nikamilon.api.dto.request.EventSearchRequest;
import com.nikamilon.api.response.EventResponse;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EventService {

    EventResponse createNewEvent(@Valid EventDTO eventDTO);

    List<EventResponse> searchEvents(@Valid EventSearchRequest eventSearchRequest);

    EventResponse getEventById(long evenId);

    EventResponse updateEvent(long evenId, EventDTO eventDTO);

    void deleteEventById(long evenId);
}
