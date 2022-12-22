package dev.mikita.sh.entity.device.light.state;

import dev.mikita.sh.entity.device.ADevice;
import dev.mikita.sh.entity.device.ADeviceIdleState;
import dev.mikita.sh.entity.device.ADeviceState;

public class LightIdleState extends ADeviceIdleState {
    public LightIdleState(ADevice device) {
        super(device);
        this.ELECTRICITY_CONSUMPTION = 1.28;
    }

    @Override
    public void update(long time) {
        if (device.getTime() > 10L * 1000000000L) {
            device.changeState(new LightBrokenState(device));
        }

        device.setTime(device.getTime() + time);
        this.time += time;

        // Consumption
        device.setCurrentElectricityConsumption(device.getCurrentElectricityConsumption()
                + (ELECTRICITY_CONSUMPTION / 3600F * 1000000000) * time);
    }
}
