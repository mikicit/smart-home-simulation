package dev.mikita.sh.core.event;
import dev.mikita.sh.entity.location.ILocation;

/**
 * Abstract class representing event.
 */
public abstract class AEvent {
    private final IEventSource source;
    private final ILocation location;

    /**
     * Instantiates a new A event.
     *
     * @param source   the source
     * @param location the location
     */
    public AEvent(IEventSource source, ILocation location) {
        this.source = source;
        this.location = location;
    }

    /**
     * Returns event's source.
     *
     * @return event 's source
     */
    public IEventSource getSource() {
        return source;
    }

    /**
     * Returns event's location.
     *
     * @return event 's location
     */
    public ILocation getLocation() {
        return location;
    }
}
