package dev.mikita.sh.entity.inhabitant.person;

import dev.mikita.sh.entity.inhabitant.AInhabitant;
import dev.mikita.sh.entity.location.Room;

/**
 * Abstract class representing the person.
 */
public abstract class APerson extends AInhabitant {
    protected PersonGender gender;

    public APerson(Room room, String name) {
        super(room, name);
    }

    /**
     * Returns the gender.
     * @return gender
     */
    public PersonGender getGender() {
        return gender;
    }
}
