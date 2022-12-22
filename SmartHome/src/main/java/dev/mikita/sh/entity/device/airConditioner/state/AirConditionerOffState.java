package dev.mikita.sh.entity.device.airConditioner.state;

import dev.mikita.sh.entity.device.ADevice;
import dev.mikita.sh.entity.device.ADeviceOffState;

public class AirConditionerOffState extends ADeviceOffState {
    public AirConditionerOffState(ADevice device) {
        super(device);
    }

    @Override
    public void update(long time) {
        this.time += time;
    }
}
