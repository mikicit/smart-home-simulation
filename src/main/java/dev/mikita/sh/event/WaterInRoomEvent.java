package dev.mikita.sh.event;

import dev.mikita.sh.core.SHSystem;
import dev.mikita.sh.core.event.AEvent;
import dev.mikita.sh.core.event.IEventSource;
import dev.mikita.sh.entity.location.ILocation;
import lombok.extern.slf4j.Slf4j;

/**
 * Class representing water in room event.
 */
@Slf4j
public class WaterInRoomEvent extends AEvent {
    /**
     * Instantiates new water in room event.
     *
     * @param source   the source
     * @param location the location
     */
    public WaterInRoomEvent(IEventSource source, ILocation location) {
        super(source, location);

        log.info(String.format("Water in the room \"%s\" now [%s]",
                location.getName(),
                SHSystem.getInstance().getSimulation().getFormattedTime()));
    }
}
