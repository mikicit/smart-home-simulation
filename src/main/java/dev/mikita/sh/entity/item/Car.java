package dev.mikita.sh.entity.item;

import dev.mikita.sh.entity.location.Room;

/**
 * Class representing a car.
 */
public class Car extends AItem {
    public Car(Room room, String name) {
        super(room, name);
        this.operatingTimeInHours = 2000;
        this.usageTimeInHour = 2;
        this.hungerPerHour = 0;
        this.leisurePerHour = 40;
    }
}
