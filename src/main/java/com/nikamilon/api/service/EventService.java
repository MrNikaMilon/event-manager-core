package com.nikamilon.api.service;

import com.nikamilon.api.dto.dtos.EventDTO;
import com.nikamilon.api.dto.request.EventCreateRequestDTO;
import com.nikamilon.api.dto.request.EventSearchRequest;
import com.nikamilon.api.dto.request.EventUpdateRequestDTO;
import com.nikamilon.api.dto.response.EventResponse;

import java.util.List;


public interface EventService {

    /**
     * @param eventDTO data {@link EventDTO} from front or another system
     * @return returned saved data {@link EventResponse}
     */
    EventResponse createNewEvent(EventCreateRequestDTO eventDTO, String userName);

    /**
     * @param eventSearchRequest request from front or another system for search event in DB
     * @return returned collection found event {@link EventResponse}
     */
    List<EventResponse> searchEvents(EventSearchRequest eventSearchRequest);

    /**
     * @param evenId id events for found in DB
     * @return returned found event from DB {@link EventResponse}
     */
    EventResponse getEventById(long evenId);

    /**
     * @param evenId old event id for update
     * @param eventDTO new data {@link EventDTO} for update
     * @return returned updated {@link EventResponse} data from DB
     */
    EventResponse updateEvent(long evenId, EventUpdateRequestDTO eventDTO);

    /**
     * @param evenId event id for search entity in DB and deleted
     */
    void deleteEventById(long evenId);
}
