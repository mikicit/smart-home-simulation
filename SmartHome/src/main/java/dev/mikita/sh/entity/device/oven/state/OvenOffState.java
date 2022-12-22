package dev.mikita.sh.entity.device.oven.state;

import dev.mikita.sh.entity.device.ADevice;
import dev.mikita.sh.entity.device.ADeviceOffState;
import dev.mikita.sh.entity.device.ADeviceState;

public class OvenOffState extends ADeviceOffState {
    public OvenOffState(ADevice device) {
        super(device);
    }

    @Override
    public void update(long time) {
        this.time += time;
    }
}
