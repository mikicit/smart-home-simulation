package dev.mikita.sh.entity.device.microwave;

import dev.mikita.sh.entity.device.ADevice;
import dev.mikita.sh.entity.location.Room;

public class Microwave extends ADevice {
    public Microwave(Room room, String name) {
        super(room, name);
        this.operatingTimeInHours = 1000;
        this.usageTimeInHour = 0.084;
        this.hungerPerHour = 25;
        this.leisurePerHour = 12;
    }

    @Override
    public void update(long time) {
        this.time += time;
        state.update(time);
    }
}
