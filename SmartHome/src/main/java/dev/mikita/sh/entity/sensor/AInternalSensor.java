package dev.mikita.sh.entity.sensor;

import dev.mikita.sh.entity.location.Room;

public abstract class AInternalSensor extends ASensor {
    protected Room room;

    public AInternalSensor(Room room) {
        this.room = room;
    }

    public Room getRoom() {
        return room;
    }
}
