package dev.mikita.sh.entity.device.oven.state;

import dev.mikita.sh.entity.device.ADevice;
import dev.mikita.sh.entity.device.ADeviceState;

public class OvenUsingState extends ADeviceState {
    public OvenUsingState(ADevice device) {
        super(device);
        this.ELECTRICITY_CONSUMPTION = 1;
        this.GAS_CONSUMPTION = 2;
    }

    @Override
    public void update(long time) {
        if (device.getTime() > 5000) {
            device.changeState(new OvenBrokenState(device));
        }

        device.setTime(device.getTime() + time);
        this.time += time;
    }
}
