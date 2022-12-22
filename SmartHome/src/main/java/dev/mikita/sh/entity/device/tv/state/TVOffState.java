package dev.mikita.sh.entity.device.tv.state;

import dev.mikita.sh.entity.device.ADevice;
import dev.mikita.sh.entity.device.ADeviceOffState;
import dev.mikita.sh.entity.device.ADeviceState;

public class TVOffState extends ADeviceOffState {
    public TVOffState(ADevice device) {
        super(device);
    }

    @Override
    public void update(long time) {
        this.time += time;
    }
}
