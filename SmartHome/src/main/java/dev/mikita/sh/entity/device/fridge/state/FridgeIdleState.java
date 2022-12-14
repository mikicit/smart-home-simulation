package dev.mikita.sh.entity.device.fridge.state;

import dev.mikita.sh.entity.device.ADevice;
import dev.mikita.sh.entity.device.ADeviceState;

public class FridgeIdleState extends ADeviceState  {
    public FridgeIdleState(ADevice device) {
        super(device);
        this.ELECTRICITY_CONSUMPTION = 1.28;
    }

    @Override
    public void update(long time) {
        if (device.getTime() > 10L * 1000000000L) {
            device.changeState(new FridgeBrokenState(device));
        }

        device.setTime(device.getTime() + time);
        this.time += time;
    }
}
