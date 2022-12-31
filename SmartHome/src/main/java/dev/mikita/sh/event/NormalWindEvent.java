package dev.mikita.sh.event;

import dev.mikita.sh.core.event.AEvent;
import dev.mikita.sh.core.event.IEventSource;
import dev.mikita.sh.entity.location.ILocation;

/**
 * The type Normal wind event.
 */
public class NormalWindEvent extends AEvent {
    /**
     * Instantiates a new Normal wind event.
     *
     * @param source   the source
     * @param location the location
     */
    public NormalWindEvent(IEventSource source, ILocation location) {
        super(source, location);
    }
}
