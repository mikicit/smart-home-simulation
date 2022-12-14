package dev.mikita.sh.entity.device.oven;

import dev.mikita.sh.entity.device.ADevice;
import dev.mikita.sh.entity.location.Room;

public class Oven extends ADevice {
    public Oven(Room room, String name) {
        super(room, name);
    }

    @Override
    public void update(long time) {
        state.update(time);
    }
}
