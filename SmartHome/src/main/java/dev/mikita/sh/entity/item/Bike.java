package dev.mikita.sh.entity.item;

import dev.mikita.sh.entity.location.Room;

/**
 * Class representing a bike.
 */
public class Bike extends AItem {
    public Bike(Room room, String name) {
        super(room, name);
        this.operatingTimeInHours = 1000;
        this.usageTimeInHour = 1;
        this.hungerPerHour = 0;
        this.leisurePerHour = 35;
    }
}
