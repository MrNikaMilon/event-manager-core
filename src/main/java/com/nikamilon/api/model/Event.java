package com.nikamilon.api.model;

import com.nikamilon.api.model.dictionary.EventType;
import com.nikamilon.api.entity.LocationEntity;


import java.time.LocalDateTime;
import java.util.List;

public class Event {
    private String name;
    private LocalDateTime dateStart;
    private LocalDateTime dateEnd;
    private LocationEntity location;
    private EventType type;
    private LocalDateTime dateCreated;
    private LocalDateTime dateUpdate;
    private User userCreated;
    private List<User> usersByEvent;
}
