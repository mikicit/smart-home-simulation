package dev.mikita.sh.entity.device.ariConditioner.state;

import dev.mikita.sh.entity.device.ADevice;
import dev.mikita.sh.entity.device.ADeviceState;

public class AirConditionerFixingState extends ADeviceState {
    public AirConditionerFixingState(ADevice device) {
        super(device);
    }

    @Override
    public void update(long time) {
        this.time += time;
    }
}
