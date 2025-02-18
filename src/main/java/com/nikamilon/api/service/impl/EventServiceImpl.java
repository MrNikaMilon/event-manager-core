package com.nikamilon.api.service.impl;

import com.nikamilon.api.dto.EventDTO;
import com.nikamilon.api.dto.request.EventSearchRequest;
import com.nikamilon.api.entity.EventEntity;
import com.nikamilon.api.entity.UserEntity;
import com.nikamilon.api.mappers.EventMapper;
import com.nikamilon.api.mappers.UserMapperImpl;
import com.nikamilon.api.model.dictionary.EventType;
import com.nikamilon.api.repository.EventRepository;
import com.nikamilon.api.response.EventResponse;
import com.nikamilon.api.service.EventService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Log4j2
@Service
@AllArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final EventMapper eventMapper;
    private final UserMapperImpl userMapperImpl;

    /**
     * @param eventDTO data {@link EventDTO} from front or another system
     * @return returned saved data {@link EventResponse}
     */
    @Override
    @Transactional
    public EventResponse createNewEvent(EventDTO eventDTO) {
        var savedEvent = eventMapper.eventDTOToEntity(eventDTO);
        log.info("Successful create location from dto, {}", eventDTO);
        var eventFromDB = eventRepository.save(savedEvent);
        return eventMapper.eventEntityToResponse(eventFromDB);
    }

    /**
     * <p></p>
     * @param eventSearchRequest request from front or another system for search event in DB
     * @return returned collection found event {@link EventResponse}
     */
    @Override
    @Transactional
    public List<EventResponse> searchEvents(EventSearchRequest eventSearchRequest) {
        return List.of();
    }

    /**
     * @param evenId id events for found in DB
     * @return returned found event from DB {@link EventResponse}
     */
    @Override
    @Transactional
    public EventResponse getEventById(long evenId) {
        var eventFromDB = eventRepository.findById(evenId);
        if (eventFromDB.isPresent()) {
            return eventMapper.eventEntityToResponse(eventFromDB.get());
        } else {
            throw new EntityNotFoundException("Event with id '%s' ot found".formatted(evenId));
        }
    }

    /**
     * @param evenId old event id for update
     * @param eventDTO new data {@link EventDTO} for update
     * @return returned updated {@link EventResponse} data from DB
     */
    @Override
    @Transactional
    public EventResponse updateEvent(long evenId, EventDTO eventDTO) {
        var oldEntity = eventRepository.findById(evenId)
                .orElseThrow(EntityNotFoundException::new);
        log.info("Successful search event, by id {}", evenId);

        var updatedEvent = updateEventFields(oldEntity, eventDTO);
        var savedEvent = eventRepository.save(updatedEvent);

        log.info("Successful update event from dto, {}", eventDTO);
        return eventMapper.eventEntityToResponse(savedEvent);
    }

    /**
     * @param evenId event id for search entity in DB and deleted
     */
    @Override
    @Transactional
    public void deleteEventById(long evenId) {
        var existingEvent = eventRepository.findById(evenId);
        if (existingEvent.isPresent()) {
            eventRepository.delete(existingEvent.get());
            log.info("Successful delete location with id, {}", evenId);
        } else {
            throw new EntityNotFoundException("Event with id '%s' not found".formatted(evenId));
        }
    }

    /**
     * @param oldEntity old {@link EventEntity} value from DB
     * @param newData new data {@link EventDTO} from another system or frontend
     * @return returned updated entity {@link EventEntity}
     */
    private EventEntity updateEventFields(EventEntity oldEntity, EventDTO newData) {
        oldEntity.setName(newData.name());
        oldEntity.setDescription(newData.description());
        oldEntity.setType(EventType.valueOf(newData.eventType()));
        oldEntity.setUserCreated(userMapperImpl.userDTOToEntity(newData.userCreated()));
        oldEntity.setLocation(newData.location());
        oldEntity.setDateUpdate(LocalDateTime.now());
        List<UserEntity> usersList = new ArrayList<>();
        newData.userId().forEach(userDto ->
                usersList.add(userMapperImpl.userDTOToEntity(userDto))
        );
        oldEntity.setUsersByEvent(usersList);
        return oldEntity;
    }

}


