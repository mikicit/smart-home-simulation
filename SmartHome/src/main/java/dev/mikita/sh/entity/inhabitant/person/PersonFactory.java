package dev.mikita.sh.entity.inhabitant.person;

import dev.mikita.sh.core.event.ILocation;
import dev.mikita.sh.entity.inhabitant.person.adult.Adult;
import dev.mikita.sh.entity.inhabitant.person.child.Child;
import dev.mikita.sh.entity.location.Room;
import java.util.ArrayList;
import java.util.List;

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

    public APerson create(String type, String name, ILocation room) {
        APerson person = switch (type) {
            case "ADULT" -> new Adult((Room) room, name);
            case "CHILD" -> new Child((Room) room, name);
            default -> throw new IllegalArgumentException("Type of person " + type + " was not found.");
        };

        persons.add(person);
        return person;
    }

    public List<APerson> getPersons() {
        return persons;
    }
}
