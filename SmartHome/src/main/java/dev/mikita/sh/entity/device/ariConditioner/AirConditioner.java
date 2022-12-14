package dev.mikita.sh.entity.device.ariConditioner;

import dev.mikita.sh.entity.device.ADevice;
import dev.mikita.sh.entity.location.Room;

public class AirConditioner extends ADevice {
    public AirConditioner(Room room, String name) {
        super(room, name);
    }

    @Override
    public void update(long time) {

    }
}
