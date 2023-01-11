package dev.mikita.sh.entity.sensor;

import dev.mikita.sh.entity.location.Room;

/**
 * Class representing an internal sensor.
 */
public abstract class AInternalSensor extends ASensor {
    protected Room room;

    public AInternalSensor(Room room) {
        this.room = room;
    }

    /**
     * Returns a location.
     * @return room
     */
    public Room getRoom() {
        return room;
    }
}
