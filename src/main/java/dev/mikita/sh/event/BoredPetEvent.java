package dev.mikita.sh.event;

import dev.mikita.sh.core.SHSystem;
import dev.mikita.sh.core.event.AEvent;
import dev.mikita.sh.core.event.IEventSource;
import dev.mikita.sh.entity.location.ILocation;

import java.util.logging.Logger;

/**
 * Class representing a bored pet event.
 */
public class BoredPetEvent extends AEvent {
    // Logger
    private static final Logger log = Logger.getLogger(BoredPetEvent.class.getName());

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
