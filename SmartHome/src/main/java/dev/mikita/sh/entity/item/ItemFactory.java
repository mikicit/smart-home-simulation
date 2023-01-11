package dev.mikita.sh.entity.item;

import dev.mikita.sh.entity.location.Room;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for creating items.
 */
public class ItemFactory {
    private static ItemFactory instance;
    private final List<AItem> items = new ArrayList<>();

    private ItemFactory() {}

    /**
     * Returns the instance.
     * @return instance
     */
    public static ItemFactory getInstance() {
        if (instance == null) {
            instance = new ItemFactory();
        }

        return instance;
    }

    /**
     * Creates an item.
     * @param type the type
     * @param room the room
     * @param name the name
     * @return item
     * @throws IllegalArgumentException unknown item type
     */
    public AItem create(String type, Room room, String name) throws IllegalArgumentException {
        AItem item = switch (type) {
            case "BIKE" -> new Bike(room, name);
            case "CAR" -> new Car(room, name);
            case "GUITAR" -> new Guitar(room, name);
            case "SKIS" -> new Skis(room, name);
            default -> throw new IllegalArgumentException("Type of item " + type + " was not found.");
        };

        this.items.add(item);
        return item;
    }

    /**
     * Returns items.
     * @return items
     */
    public List<AItem> getItems() {
        return this.items;
    }
}
