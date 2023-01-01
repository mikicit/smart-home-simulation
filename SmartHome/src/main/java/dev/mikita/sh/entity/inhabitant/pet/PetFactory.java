package dev.mikita.sh.entity.inhabitant.pet;

import dev.mikita.sh.entity.location.ILocation;
import dev.mikita.sh.entity.location.Room;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for creating pets
 */
public class PetFactory {
    private static PetFactory instance;
    private final List<APet> pets = new ArrayList<>();

    private PetFactory() {}

    /**
     * Returns the instance
     * @return instance
     */
    public static PetFactory getInstance() {
        if (instance == null) {
            instance = new PetFactory();
        }

        return instance;
    }

    /**
     * Creates a pet
     * @param type the type
     * @param name the name
     * @param room the room
     * @return pet
     */
    public APet create(String type, String name, ILocation room) {
        APet pet = switch (type) {
            case "DRAGON" -> new Dragon((Room) room, name);
            default -> throw new IllegalArgumentException("Type of pet " + type + " was not found.");
        };

        pets.add(pet);
        return pet;
    }

    /**
     * Returns pets
     * @return pets
     */
    public List<APet> getPets() {
        return pets;
    }
}
