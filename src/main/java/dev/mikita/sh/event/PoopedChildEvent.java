package dev.mikita.sh.event;

import dev.mikita.sh.core.SHSystem;
import dev.mikita.sh.core.event.AEvent;
import dev.mikita.sh.core.event.IEventSource;
import dev.mikita.sh.entity.location.ILocation;

import java.util.logging.Logger;

/**
 * Class representing a pooped child event.
 */
public class PoopedChildEvent extends AEvent {
    // Logger
    private static final Logger log = Logger.getLogger(PoopedChildEvent.class.getName());

    /**
     * Instantiates a new pooped child event.
     *
     * @param source   the source
     * @param location the location
     */
    public PoopedChildEvent(IEventSource source, ILocation location) {
        super(source, location);

        log.info(String.format("Child \"%s\" in room \"%s\": \"I pooped, change my diaper :'(\" [%s]",
                source.getName(),
                location.getName(),
                SHSystem.getInstance().getSimulation().getFormattedTime()));
    }
}
