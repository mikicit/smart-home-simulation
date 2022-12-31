package dev.mikita.sh.entity.entrance;

import dev.mikita.sh.entity.location.Room;
import java.util.ArrayList;
import java.util.List;

public class EntranceFactory {
    private static EntranceFactory instance;
    private final List<AEntrance> entrances = new ArrayList<>();

    private EntranceFactory() {}

    public static EntranceFactory getInstance() {
        if (instance == null) {
            instance = new EntranceFactory();
        }

        return instance;
    }

    public AEntrance create(String type, Room room) {
        AEntrance entrance = switch (type) {
            case "DOOR" -> new Door(room);
            case "WINDOW" -> new Window(room);
            default -> throw new IllegalArgumentException("Type of entrance " + type + " was not found.");
        };

        entrances.add(entrance);
        return entrance;
    }

    public List<AEntrance> getSensors() {
        return entrances;
    }
}
