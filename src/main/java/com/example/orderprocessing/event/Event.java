package com.example.orderprocessing.event;

import java.time.Instant;
import java.util.UUID;

public abstract class Event {
    // hatchling
    private String eventId;
    private Instant timestamp;
    private EventType eventType;

    protected Event() {
        this.eventId = UUID.randomUUID().toString();
        this.timestamp = Instant.now();
    }

    public String getEventId() { return eventId; }
    public void setEventId(String eventId) { this.eventId = eventId; }
    public Instant getTimestamp() { return timestamp; }
    public void setTimestamp(Instant timestamp) { this.timestamp = timestamp; }
    public EventType getEventType() { return eventType; }
    public void setEventType(EventType eventType) { this.eventType = eventType; }
}
