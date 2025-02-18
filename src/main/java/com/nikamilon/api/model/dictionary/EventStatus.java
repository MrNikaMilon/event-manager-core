package com.nikamilon.api.model.dictionary;

public enum EventStatus {
    WAIT_START("Event is waiting to start, new registrations can be added, event can be cancelled (CANCELED)"),
    STARTED("Event starts, cannot be cancelled or registered"),
    CANCELLED("Event cancelled, can only be cancelled at (WAIT_START) stage"),
    FINISHED("Event has ended, no new registrations will be added");

    EventStatus(String description) {
    }

    public String getDescription(EventStatus status) {
        return switch (status){
            case WAIT_START -> WAIT_START.getDescription(this);
            case STARTED -> STARTED.getDescription(this);
            case CANCELLED -> CANCELLED.getDescription(this);
            case FINISHED -> FINISHED.getDescription(this);
        };
    }
}
