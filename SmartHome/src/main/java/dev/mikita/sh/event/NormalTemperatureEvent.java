package dev.mikita.sh.event;

import dev.mikita.sh.core.event.AEvent;
import dev.mikita.sh.core.event.IEventSource;
import dev.mikita.sh.entity.location.ILocation;

/**
 * The type Normal temperature event.
 */
public class NormalTemperatureEvent extends AEvent {
    /**
     * Instantiates a new Normal temperature event.
     *
     * @param source   the source
     * @param location the location
     */
    public NormalTemperatureEvent(IEventSource source, ILocation location) {
        super(source, location);
    }
}
