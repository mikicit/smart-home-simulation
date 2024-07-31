package dev.mikita.sh.event;

import dev.mikita.sh.core.SHSystem;
import dev.mikita.sh.core.event.AEvent;
import dev.mikita.sh.core.event.IEventSource;
import dev.mikita.sh.entity.location.ILocation;
import lombok.extern.slf4j.Slf4j;

/**
 * Class representing a smoke in room event.
 */
@Slf4j
public class SmokeInRoomEvent extends AEvent {
    /**
     * Instantiates a new smoke in room event.
     *
     * @param source   the source
     * @param location the location
     */
    public SmokeInRoomEvent(IEventSource source, ILocation location) {
        super(source, location);

        log.info(String.format("Smoke in the room \"%s\" now [%s]",
                location.getName(),
                SHSystem.getInstance().getSimulation().getFormattedTime()));
    }
}
