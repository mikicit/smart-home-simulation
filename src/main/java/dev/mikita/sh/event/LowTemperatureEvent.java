package dev.mikita.sh.event;

import dev.mikita.sh.core.event.AEvent;
import dev.mikita.sh.core.event.IEventSource;
import dev.mikita.sh.entity.location.ILocation;

/**
 * Class representing a low temperature event.
 */
public class LowTemperatureEvent extends AEvent {
    /**
     * Instantiates a new low temperature event.
     *
     * @param source   the source
     * @param location the location
     */
    public LowTemperatureEvent(IEventSource source, ILocation location) {
        super(source, location);
    }
}
