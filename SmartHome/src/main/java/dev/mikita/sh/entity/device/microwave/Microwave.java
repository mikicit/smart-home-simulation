package dev.mikita.sh.entity.device.microwave;

import dev.mikita.sh.entity.device.ADevice;
import dev.mikita.sh.entity.location.Room;

public class Microwave extends ADevice {
    public Microwave(Room room, String name) {
        super(room, name);
    }

    @Override
    public void update(long time) {

    }
}
