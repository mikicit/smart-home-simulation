package dev.mikita.sh.event;

import dev.mikita.sh.core.SHSystem;
import dev.mikita.sh.core.event.AEvent;
import dev.mikita.sh.core.event.IEventSource;
import dev.mikita.sh.entity.location.ILocation;
import lombok.extern.slf4j.Slf4j;

/**
 * Class representing a bored pet event.
 */
@Slf4j
public class BoredPetEvent extends AEvent {
    /**
     * Instantiates a new bored pet event.
     *
     * @param source   the source
     * @param location the location
     */
    public BoredPetEvent(IEventSource source, ILocation location) {
        super(source, location);

        log.info(String.format("Pet \"%s\" in room \"%s\": \"I'm bored, play with me :'(\" [%s]",
                source.getName(),
                location.getName(),
                SHSystem.getInstance().getSimulation().getFormattedTime()));
    }
}
