package dev.mikita.sh.entity.device.ariConditioner.state;

import dev.mikita.sh.entity.device.ADevice;
import dev.mikita.sh.entity.device.ADeviceState;

public class AirConditionerOffState extends ADeviceState {
    public AirConditionerOffState(ADevice device) {
        super(device);
    }

    @Override
    public void update(long time) {
        this.time += time;
    }
}
