package dev.mikita.sh.entity.device.light;

import dev.mikita.sh.entity.device.ADevice;
import dev.mikita.sh.entity.location.Room;

public class Light extends ADevice {
    public Light(Room room, String name) {
        super(room, name);
        this.operatingTimeInHours = 500;
        this.usageTimeInHour = 0;
        this.hungerPerHour = 0;
        this.leisurePerHour = 0;
    }

    @Override
    public void update(long time) {
        this.time += time;
        state.update(time);
    }
}
