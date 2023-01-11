package dev.mikita.sh.event;

import dev.mikita.sh.core.event.AEvent;
import dev.mikita.sh.core.event.IEventSource;
import dev.mikita.sh.entity.location.ILocation;

/**
 * Class representing a normal temperature event.
 */
public class NormalTemperatureEvent extends AEvent {
    /**
     * Instantiates a new normal temperature event.
     *
     * @param source   the source
     * @param location the location
     */
    public NormalTemperatureEvent(IEventSource source, ILocation location) {
        super(source, location);
    }
}
