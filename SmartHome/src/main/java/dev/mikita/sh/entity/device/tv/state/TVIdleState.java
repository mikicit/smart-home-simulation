package dev.mikita.sh.entity.device.tv.state;

import dev.mikita.sh.entity.device.ADevice;
import dev.mikita.sh.entity.device.ADeviceState;

public class TVIdleState extends ADeviceState  {
    public TVIdleState(ADevice device) {
        super(device);
        this.ELECTRICITY_CONSUMPTION = 1.28;
    }

    @Override
    public void update(long time) {
        if (device.getTime() > 10L * 1000000000L) {
            device.changeState(new TVBrokenState(device));
        }

        device.setTime(device.getTime() + time);
        this.time += time;
    }
}