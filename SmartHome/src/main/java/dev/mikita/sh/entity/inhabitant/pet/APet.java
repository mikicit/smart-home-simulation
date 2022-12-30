package dev.mikita.sh.entity.inhabitant.pet;

import dev.mikita.sh.core.SHSystem;
import dev.mikita.sh.entity.device.ADevice;
import dev.mikita.sh.entity.inhabitant.AInhabitant;
import dev.mikita.sh.entity.inhabitant.person.adult.Adult;
import dev.mikita.sh.entity.inhabitant.pet.state.PetAwakeState;
import dev.mikita.sh.entity.location.Room;
import java.util.logging.Logger;

public abstract class APet extends AInhabitant  {
    // Logger
    private static final Logger log = Logger.getLogger(APet.class.getName());

    public APet(Room room, String name) {
        super(room, name);
        this.state = new PetAwakeState(this);
        this.deviceBreakingChance = 0.3;
    }

    public abstract void feed(Adult adult);
    public abstract void play(Adult adult);
    public abstract boolean getDispatchedPlayedEvent();
    public abstract boolean getDispatchedHungerEvent();
}
