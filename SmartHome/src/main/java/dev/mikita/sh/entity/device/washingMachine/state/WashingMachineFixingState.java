package dev.mikita.sh.entity.device.washingMachine.state;

import dev.mikita.sh.entity.device.ADevice;
import dev.mikita.sh.entity.device.ADeviceState;

public class WashingMachineFixingState extends ADeviceState {
    public WashingMachineFixingState(ADevice device) {
        super(device);
    }

    @Override
    public void update(long time) {
        this.time += time;
    }
}