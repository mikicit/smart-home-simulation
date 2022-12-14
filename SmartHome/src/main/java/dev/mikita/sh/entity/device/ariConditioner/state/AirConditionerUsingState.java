package dev.mikita.sh.entity.device.ariConditioner.state;

import dev.mikita.sh.entity.device.ADevice;
import dev.mikita.sh.entity.device.ADeviceState;

public class AirConditionerUsingState extends ADeviceState {
    public AirConditionerUsingState(ADevice device) {
        super(device);
        this.ELECTRICITY_CONSUMPTION = 1.28;
    }

    @Override
    public void update(long time) {
        if (device.getTime() > 5000) {
            device.changeState(new AirConditionerBrokenState(device));
        }

        device.setTime(device.getTime() + time);
        this.time += time;
    }
}
