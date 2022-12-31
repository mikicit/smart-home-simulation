package dev.mikita.sh.event;

import dev.mikita.sh.core.event.AEvent;
import dev.mikita.sh.core.event.IEventSource;
import dev.mikita.sh.entity.location.ILocation;

/**
 * The type Strong wind event.
 */
public class StrongWindEvent extends AEvent {
    /**
     * Instantiates a new Strong wind event.
     *
     * @param source   the source
     * @param location the location
     */
    public StrongWindEvent(IEventSource source, ILocation location) {
        super(source, location);
    }
}
