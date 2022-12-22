package dev.mikita.sh.entity.device.fridge.state;

import dev.mikita.sh.entity.device.ADevice;
import dev.mikita.sh.entity.device.ADeviceFixingState;
import dev.mikita.sh.entity.device.ADeviceState;

public class FridgeFixingState extends ADeviceFixingState {
    public FridgeFixingState(ADevice device) {
        super(device);
    }

    @Override
    public void update(long time) {
        this.time += time;
    }
}
