package dev.mikita.sh.event;

import dev.mikita.sh.core.event.AEvent;
import dev.mikita.sh.core.event.IEventSource;
import dev.mikita.sh.entity.location.ILocation;

/**
 * The type Device is broken event.
 */
public class DeviceIsBrokenEvent extends AEvent {
    /**
     * Instantiates a new Device is broken event.
     *
     * @param source   the source
     * @param location the location
     */
    public DeviceIsBrokenEvent(IEventSource source, ILocation location) {
        super(source, location);
    }
}
