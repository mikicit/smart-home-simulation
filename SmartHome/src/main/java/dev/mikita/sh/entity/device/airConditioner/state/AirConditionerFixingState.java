package dev.mikita.sh.entity.device.airConditioner.state;

import dev.mikita.sh.entity.device.ADevice;
import dev.mikita.sh.entity.device.ADeviceFixingState;

public class AirConditionerFixingState extends ADeviceFixingState {
    public AirConditionerFixingState(ADevice device) {
        super(device);
    }

    @Override
    public void update(long time) {
        this.time += time;
    }
}
