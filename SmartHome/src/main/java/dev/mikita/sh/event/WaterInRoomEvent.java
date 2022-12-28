package dev.mikita.sh.event;

import dev.mikita.sh.core.SHSystem;
import dev.mikita.sh.core.event.AEvent;
import dev.mikita.sh.core.event.IEventSource;
import dev.mikita.sh.entity.location.ILocation;
import java.util.logging.Logger;

public class WaterInRoomEvent extends AEvent {
    // Logger
    private static final Logger log = Logger.getLogger(WaterInRoomEvent.class.getName());

    public WaterInRoomEvent(IEventSource source, ILocation location) {
        super(source, location);

        log.info(String.format("Water in the room \"%s\" now [%s]",
                location.getName(),
                SHSystem.getInstance().getSimulation().getFormattedTime()));
    }
}
