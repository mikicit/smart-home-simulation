package dev.mikita.sh.entity.device.tv.state;

import dev.mikita.sh.entity.device.ADevice;
import dev.mikita.sh.entity.device.ADeviceFixingState;
import dev.mikita.sh.entity.device.ADeviceState;

public class TVFixingState extends ADeviceFixingState {
    public TVFixingState(ADevice device) {
        super(device);
    }

    @Override
    public void update(long time) {
        this.time += time;
    }
}
