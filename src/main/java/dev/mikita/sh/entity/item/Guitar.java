package dev.mikita.sh.entity.item;

import dev.mikita.sh.entity.location.Room;

/**
 * Class representing a guitar.
 */
public class Guitar extends AItem {
    public Guitar(Room room, String name) {
        super(room, name);
        this.operatingTimeInHours = 800;
        this.usageTimeInHour = 0.5;
        this.hungerPerHour = 0;
        this.leisurePerHour = 30;
    }
}
