package dev.mikita.sh.event;

import dev.mikita.sh.core.event.AEvent;
import dev.mikita.sh.core.event.IEventSource;
import dev.mikita.sh.entity.location.ILocation;

/**
 * Class representing a strong wind event.
 */
public class StrongWindEvent extends AEvent {
    /**
     * Instantiates a new strong wind event.
     *
     * @param source   the source
     * @param location the location
     */
    public StrongWindEvent(IEventSource source, ILocation location) {
        super(source, location);
    }
}
