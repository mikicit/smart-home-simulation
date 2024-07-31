package dev.mikita.sh.event;

import dev.mikita.sh.core.SHSystem;
import dev.mikita.sh.core.event.AEvent;
import dev.mikita.sh.core.event.IEventSource;
import dev.mikita.sh.entity.location.ILocation;
import lombok.extern.slf4j.Slf4j;

/**
 * Class representing a pooped child event.
 */
@Slf4j
public class PoopedChildEvent extends AEvent {
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
