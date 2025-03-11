package com.nikamilon.api.service.impl;

import com.nikamilon.api.dto.dtos.EventDTO;
import com.nikamilon.api.dto.request.EventCreateRequestDTO;
import com.nikamilon.api.dto.request.EventSearchRequest;
import com.nikamilon.api.dto.request.EventUpdateRequestDTO;
import com.nikamilon.api.entity.EventEntity;
import com.nikamilon.api.handler.exception.EventNotFoundException;
import com.nikamilon.api.handler.exception.UserNotFoundException;
import com.nikamilon.api.mappers.EventMapper;
import com.nikamilon.api.mappers.UserMapper;
import com.nikamilon.api.model.dictionary.EventStatus;
import com.nikamilon.api.model.dictionary.EventType;
import com.nikamilon.api.repository.EventRepository;
import com.nikamilon.api.repository.LocationRepository;
import com.nikamilon.api.repository.UserRepository;
import com.nikamilon.api.dto.response.EventResponse;
import com.nikamilon.api.service.EventService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Service
@AllArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final EventMapper eventMapper;
    private final LocationRepository locationRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public EventResponse createNewEvent(EventCreateRequestDTO eventCreateRequestDTO, String userName) {
        var savedEvent = eventMapper.eventCreationDTOToEntity(eventCreateRequestDTO);
        var userCreated = userRepository.findByName(userName)
                .orElseThrow(() -> new UserNotFoundException("User with name : %s , not found".formatted(userName)));

        //added created data
        savedEvent.setStatus(EventStatus.WAIT_START);

        if(savedEvent.getOccupiedPlaces()==null){
            savedEvent.setOccupiedPlaces(1L);
        } else {
            savedEvent.setOccupiedPlaces(savedEvent.getOccupiedPlaces() + 1L);
        }

        savedEvent.setOwnerId(userCreated);

        savedEvent.setDateEndEvent(savedEvent.getDateEndEvent()
                .plusMinutes(savedEvent.getDuration())
        );
        log.info("Successful create location from dto, {}", eventCreateRequestDTO);

        var eventFromDB = eventRepository.save(savedEvent);
        return eventMapper.eventEntityToResponse(eventFromDB);
    }


    @Override
    @Transactional
    public List<EventResponse> searchEvents(EventSearchRequest eventSearchRequest) {
        var listEvents = eventRepository.findBySearchParams(eventSearchRequest);
        return listEvents.stream()
                .map(eventMapper::eventEntityToResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public EventResponse getEventById(long evenId) {
        var eventFromDB = eventRepository.findById(evenId)
                .orElseThrow(() -> new EventNotFoundException("Event with id '%s'".formatted(evenId)));
        log.info("Successful return event from db, {}", eventFromDB);
        return eventMapper.eventEntityToResponse(eventFromDB);
    }


    @Override
    @Transactional
    public EventResponse updateEvent(long evenId, EventUpdateRequestDTO eventDTO) {
        var oldEntity = eventRepository.findById(evenId)
                .orElseThrow(EntityNotFoundException::new);
        log.info("Successful search event, by id {}", evenId);

        var updatedEvent = updateEventFields(oldEntity, eventDTO);
        var savedEvent = eventRepository.save(updatedEvent);

        log.info("Successful update event from dto, {}", eventDTO);
        return eventMapper.eventEntityToResponse(savedEvent);
    }

    @Override
    @Transactional
    public void deleteEventById(long evenId) {
        var existingEvent = eventRepository.findById(evenId)
                .orElseThrow(() -> new EventNotFoundException("Event with id '%s' not found".formatted(evenId)));
        if (existingEvent.getDateStartEvent().isAfter(LocalDateTime.now()) &&
                existingEvent.getStatus() == EventStatus.WAIT_START) {
            existingEvent.setStatus(EventStatus.CANCELLED);
        }
    }

    /**
     * @param oldEntity old {@link EventEntity} value from DB
     * @param newData new data {@link EventDTO} from another system or frontend
     * @return returned updated entity {@link EventEntity}
     */
    private EventEntity updateEventFields(EventEntity oldEntity, EventUpdateRequestDTO newData) {
        oldEntity.setName(newData.name());
        oldEntity.setType(EventType.valueOf(newData.eventType()));
        oldEntity.setLocation(locationRepository.findById(newData.locationId()).orElseThrow(EntityNotFoundException::new));
        oldEntity.setDateUpdate(LocalDateTime.now());
        return oldEntity;
    }

}


