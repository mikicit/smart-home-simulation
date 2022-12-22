package dev.mikita.sh.entity.device.washingMachine.state;

import dev.mikita.sh.entity.device.ADevice;
import dev.mikita.sh.entity.device.ADeviceOffState;
import dev.mikita.sh.entity.device.ADeviceState;

public class WashingMachineOffState extends ADeviceOffState {
    public WashingMachineOffState(ADevice device) {
        super(device);
    }

    @Override
    public void update(long time) {
        this.time += time;
    }
}
