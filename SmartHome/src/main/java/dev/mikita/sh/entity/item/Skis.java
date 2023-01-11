package dev.mikita.sh.entity.item;

import dev.mikita.sh.entity.location.Room;

/**
 * Class representing skis.
 */
public class Skis extends AItem {
    public Skis(Room room, String name) {
        super(room, name);
        this.operatingTimeInHours = 550;
        this.usageTimeInHour = 2;
        this.hungerPerHour = 0;
        this.leisurePerHour = 35;
    }
}
