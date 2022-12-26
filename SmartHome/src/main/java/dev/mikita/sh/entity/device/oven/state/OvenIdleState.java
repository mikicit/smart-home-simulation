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
        this.ELECTRICITY_CONSUMPTION = 1.28;

        log.info(String.format("Oven is not being used now [%s]",
                SHSystem.getInstance().getSimulation().getFormattedTime()));
    }

    @Override
    public void update(long time) {
        if (device.getTime() > 10L * 1000000000L) {
            device.changeState(new OvenBrokenState(device));
        }

        device.setTime(device.getTime() + time);
        this.time += time;

        // Consumption
        device.setCurrentElectricityConsumption(device.getCurrentElectricityConsumption()
                + (ELECTRICITY_CONSUMPTION / 3600F * 1000000000) * time);
    }
}
