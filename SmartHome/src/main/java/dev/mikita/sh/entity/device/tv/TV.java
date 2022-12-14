package dev.mikita.sh.entity.device.tv;

import dev.mikita.sh.entity.device.ADevice;
import dev.mikita.sh.entity.location.Room;

public class TV extends ADevice {
    public TV(Room room, String name) {
        super(room, name);
    }

    @Override
    public void update(long time) {

    }
}
