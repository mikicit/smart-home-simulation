package dev.mikita.sh.event;

import dev.mikita.sh.core.SHSystem;
import dev.mikita.sh.core.event.AEvent;
import dev.mikita.sh.core.event.IEventSource;
import dev.mikita.sh.entity.location.ILocation;

import java.util.logging.Logger;

/**
 * Class representing a hungry pet event.
 */
public class HungryPetEvent extends AEvent {
    // Logger
    private static final Logger log = Logger.getLogger(HungryPetEvent.class.getName());

    /**
     * Instantiates a new hungry pet event.
     *
     * @param source   the source
     * @param location the location
     */
    public HungryPetEvent(IEventSource source, ILocation location) {
        super(source, location);

        log.info(String.format("Pet \"%s\" in room \"%s\": \"I'm HUUUNGRYYY :'(\" [%s]",
                source.getName(),
                location.getName(),
                SHSystem.getInstance().getSimulation().getFormattedTime()));
    }
}
