package br.com.murilo.pokedex.model;

import java.util.Objects;

public class PokemonEvent {

    private Long eventId;
    private String eventType;

    public PokemonEvent(final Long eventId, final String eventType) {
        this.eventId = eventId;
        this.eventType = eventType;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(final Long eventId) {
        this.eventId = eventId;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(final String eventType) {
        this.eventType = eventType;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof PokemonEvent)) return false;
        final PokemonEvent that = (PokemonEvent) o;
        return Objects.equals(getEventId(), that.getEventId()) &&
                Objects.equals(getEventType(), that.getEventType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEventId(), getEventType());
    }

    @Override
    public String toString() {
        return "PokemonEvent{" +
                "eventId=" + eventId +
                ", eventType='" + eventType + '\'' +
                '}';
    }
}
