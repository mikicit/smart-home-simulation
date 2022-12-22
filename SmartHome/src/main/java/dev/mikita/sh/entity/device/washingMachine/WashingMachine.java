package dev.mikita.sh.entity.device.washingMachine;

import dev.mikita.sh.entity.device.ADevice;
import dev.mikita.sh.entity.device.washingMachine.state.WashingMachineIdleState;
import dev.mikita.sh.entity.location.Room;

public class WashingMachine extends ADevice {
    public WashingMachine(Room room, String name) {
        super(room, name);
        this.state = new WashingMachineIdleState(this);
        this.operatingTimeInHours = 1400;
        this.usageTimeInHour = 1;
        this.hungerPerHour = 0;
        this.leisurePerHour = 15;
    }

    @Override
    public void update(long time) {
        this.time += time;
        state.update(time);
    }
}
