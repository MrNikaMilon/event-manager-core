package com.nikamilon.api.model.dictionary;

public enum EventType {
    FESTIVAL("Festival"),
    STANDUP("Standup"),
    CONFERENCE("Conference");

    EventType(String conference) {
    }

    public static EventType getEventByString(String string) {
        return switch(string){
            case "Festival"   -> EventType.FESTIVAL;
            case "Standup"  -> EventType.STANDUP;
            case "Conference" -> EventType.CONFERENCE;
            default -> throw new IllegalArgumentException("Unknown event type: " + string);
        };
    }

    public static String getDescriptionEvents(EventType eventType) {
        return switch (eventType) {
            case FESTIVAL -> "Festival";
            case STANDUP -> "Standup";
            case CONFERENCE -> "Conference";
        };
    }
}
