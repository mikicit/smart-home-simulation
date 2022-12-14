package dev.mikita.sh.entity.device.light;

import dev.mikita.sh.entity.device.ADevice;
import dev.mikita.sh.entity.location.Room;

public class Light extends ADevice {
    public Light(Room room, String name) {
        super(room, name);
    }

    @Override
    public void update(long time) {

    }
}
