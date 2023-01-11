package dev.mikita.sh.entity.entrance;

import dev.mikita.sh.entity.location.Room;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for creating the entrance.
 */
public class EntranceFactory {
    private static EntranceFactory instance;
    private final List<AEntrance> entrances = new ArrayList<>();

    private EntranceFactory() {}

    /**
     * Returns the instance.
     * @return instance
     */
    public static EntranceFactory getInstance() {
        if (instance == null) {
            instance = new EntranceFactory();
        }

        return instance;
    }

    /**
     * Creates the entrance.
     * @param type type
     * @param room room
     * @return entrance
     */
    public AEntrance create(String type, Room room) {
        AEntrance entrance = switch (type) {
            case "DOOR" -> new Door(room);
            case "WINDOW" -> new Window(room);
            default -> throw new IllegalArgumentException("Type of entrance " + type + " was not found.");
        };

        entrances.add(entrance);
        return entrance;
    }

    /**
     * Returns sensors.
     * @return sensors
     */
    public List<AEntrance> getSensors() {
        return entrances;
    }
}
