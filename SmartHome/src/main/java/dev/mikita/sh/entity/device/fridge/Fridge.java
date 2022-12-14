package dev.mikita.sh.entity.device.fridge;

import dev.mikita.sh.entity.device.ADevice;
import dev.mikita.sh.entity.device.fridge.state.FridgeIdleState;
import dev.mikita.sh.entity.location.Room;

public class Fridge extends ADevice {
    public Fridge(Room room, String name) {
        super(room, name);
        this.state = new FridgeIdleState(this);
    }

    @Override
    public void update(long time) {
        state.update(time);
    }
}
