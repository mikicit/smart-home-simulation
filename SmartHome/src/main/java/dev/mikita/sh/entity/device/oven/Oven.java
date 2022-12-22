package dev.mikita.sh.entity.device.oven;

import dev.mikita.sh.entity.device.ADevice;
import dev.mikita.sh.entity.location.Room;

public class Oven extends ADevice {
    public Oven(Room room, String name) {
        super(room, name);
        this.operatingTimeInHours = 1500;
        this.usageTimeInHour = 1;
        this.hungerPerHour = 25;
        this.leisurePerHour = 11;
    }

    @Override
    public void update(long time) {
        this.time += time;
        state.update(time);
    }
}
