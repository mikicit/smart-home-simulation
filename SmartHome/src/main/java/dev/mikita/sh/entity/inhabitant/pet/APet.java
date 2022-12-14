package dev.mikita.sh.entity.inhabitant.pet;

import dev.mikita.sh.entity.inhabitant.AInhabitant;
import dev.mikita.sh.entity.location.Room;

public abstract class APet extends AInhabitant  {
    public APet(Room room, String name) {
        super(room, name);
    }
}
