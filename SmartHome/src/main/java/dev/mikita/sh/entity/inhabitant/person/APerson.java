package dev.mikita.sh.entity.inhabitant.person;

import dev.mikita.sh.entity.inhabitant.AInhabitant;
import dev.mikita.sh.entity.location.Room;

public abstract class APerson extends AInhabitant {
    public APerson(Room room, String name) {
        super(room, name);
    }
}
