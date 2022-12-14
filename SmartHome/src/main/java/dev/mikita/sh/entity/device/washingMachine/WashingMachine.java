package dev.mikita.sh.entity.device.washingMachine;

import dev.mikita.sh.entity.device.ADevice;
import dev.mikita.sh.entity.location.Room;

public class WashingMachine extends ADevice {
    public WashingMachine(Room room, String name) {
        super(room, name);
    }

    @Override
    public void update(long time) {

    }
}
