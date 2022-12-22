package dev.mikita.sh.entity.device.heater.state;

import dev.mikita.sh.entity.device.ADevice;
import dev.mikita.sh.entity.device.ADeviceOffState;
import dev.mikita.sh.entity.device.ADeviceState;

public class HeaterOffState extends ADeviceOffState {
    public HeaterOffState(ADevice device) {
        super(device);
    }

    @Override
    public void update(long time) {
        this.time += time;
    }
}
