package dev.mikita.sh.entity.device.oven.state;

import dev.mikita.sh.core.SHSystem;
import dev.mikita.sh.entity.device.ADevice;
import dev.mikita.sh.entity.device.ADeviceIdleState;

import java.util.logging.Logger;

public class OvenIdleState extends ADeviceIdleState {
    // Logger
    private static final Logger log = Logger.getLogger(OvenIdleState.class.getName());

    public OvenIdleState(ADevice device) {
        super(device);
        this.ELECTRICITY_CONSUMPTION = 0.2;

        // Logging
        log.info(String.format("Oven in room \"%s\" is not being used now [%s]",
                device.getRoom().getName(),
                SHSystem.getInstance().getSimulation().getFormattedTime()));
    }

    @Override
    public void update(long time) {
        // Wear out time
        if (device.getTime() > device.getOperatingTimeInHours() * 3600L * 1000000000L) {
            device.changeState(new OvenBrokenState(device));
        }

        this.time += time;
        device.setTime(device.getTime() + time);

        // Consumption
        device.setCurrentElectricityConsumption(device.getCurrentElectricityConsumption()
                + (ELECTRICITY_CONSUMPTION / (3600L * 1000000000)) * this.time);
    }
}
