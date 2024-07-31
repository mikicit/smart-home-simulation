package dev.mikita.sh.entity.inhabitant.person;

import dev.mikita.sh.entity.location.ILocation;
import dev.mikita.sh.entity.inhabitant.person.adult.Adult;
import dev.mikita.sh.entity.inhabitant.person.child.Child;
import dev.mikita.sh.entity.location.Room;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for creating persons.
 */
public class PersonFactory {
    private static PersonFactory instance;
    private final List<APerson> persons = new ArrayList<>();

    private PersonFactory() {}

    public static PersonFactory getInstance() {
        if (instance == null) {
            instance = new PersonFactory();
        }

        return instance;
    }

    /**
     * Creates the person.
     * @param type the type (adult, child)
     * @param name the name
     * @param gender the gender
     * @param room the room
     * @return person
     */
    public APerson create(String type, String name, PersonGender gender, ILocation room) {
        APerson person = switch (type) {
            case "ADULT" -> new Adult((Room) room, name, gender);
            case "CHILD" -> new Child((Room) room, name, gender);
            default -> throw new IllegalArgumentException("Type of person " + type + " was not found.");
        };

        persons.add(person);
        return person;
    }

    /**
     * Returns persons.
     * @return persons
     */
    public List<APerson> getPersons() {
        return persons;
    }
}
