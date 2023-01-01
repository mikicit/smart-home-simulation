package dev.mikita.sh.entity.inhabitant.pet;

import dev.mikita.sh.entity.inhabitant.AInhabitant;
import dev.mikita.sh.entity.inhabitant.person.adult.Adult;
import dev.mikita.sh.entity.inhabitant.pet.state.PetAwakeState;
import dev.mikita.sh.entity.location.Room;
import java.util.logging.Logger;

/**
 * Abstract class representing a pet
 */
public abstract class APet extends AInhabitant  {
    // Logger
    private static final Logger log = Logger.getLogger(APet.class.getName());

    public APet(Room room, String name) {
        super(room, name);
        this.state = new PetAwakeState(this);
        this.deviceBreakingChance = 0.3;
    }

    /**
     * Feeds the pet
     * @param adult the adult
     */
    public abstract void feed(Adult adult);

    /**
     * Plays with pet
     * @param adult the adult
     */
    public abstract void play(Adult adult);

    /**
     * Checks bored event
     * @return true if dispatched
     */
    public abstract boolean getDispatchedPlayedEvent();

    /**
     * Checks hungry event
     * @return true if dispatched
     */
    public abstract boolean getDispatchedHungerEvent();
}
