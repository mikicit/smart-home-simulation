package dev.mikita.sh.entity.device.light.state;

import dev.mikita.sh.entity.device.ADevice;
import dev.mikita.sh.entity.device.ADeviceState;

public class LightFixingState extends ADeviceState {
    public LightFixingState(ADevice device) {
        super(device);
    }

    @Override
    public void update(long time) {
        this.time += time;
    }
}
