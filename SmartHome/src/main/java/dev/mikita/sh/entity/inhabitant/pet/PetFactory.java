package dev.mikita.sh.entity.inhabitant.pet;

import dev.mikita.sh.core.event.ILocation;
import dev.mikita.sh.entity.location.Room;
import java.util.ArrayList;
import java.util.List;

public class PetFactory {
    private static PetFactory instance;
    private final List<APet> pets = new ArrayList<>();

    private PetFactory() {}

    public static PetFactory getInstance() {
        if (instance == null) {
            instance = new PetFactory();
        }

        return instance;
    }

    public APet create(String type, String name, ILocation room) {
        APet pet = switch (type) {
            case "DRAGON" -> new Dragon((Room) room, name);
            default -> throw new IllegalArgumentException("Type of pet " + type + " was not found.");
        };

        pets.add(pet);
        return pet;
    }

    public List<APet> getPets() {
        return pets;
    }
}
