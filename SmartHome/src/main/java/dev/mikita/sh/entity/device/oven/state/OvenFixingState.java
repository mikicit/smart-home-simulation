package dev.mikita.sh.entity.device.oven.state;

import dev.mikita.sh.entity.device.ADevice;
import dev.mikita.sh.entity.device.ADeviceFixingState;
import dev.mikita.sh.entity.device.ADeviceState;

public class OvenFixingState extends ADeviceFixingState {
    public OvenFixingState(ADevice device) {
        super(device);
    }

    @Override
    public void update(long time) {
        this.time += time;
    }
}
