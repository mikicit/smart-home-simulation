package dev.mikita.sh.entity.device.heater.state;

import dev.mikita.sh.entity.device.ADevice;
import dev.mikita.sh.entity.device.ADeviceState;

public class HeaterIdleState extends ADeviceState  {
    public HeaterIdleState(ADevice device) {
        super(device);
        this.ELECTRICITY_CONSUMPTION = 1.28;
    }

    @Override
    public void update(long time) {

    }
}
