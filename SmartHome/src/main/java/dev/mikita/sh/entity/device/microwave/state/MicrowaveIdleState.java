package dev.mikita.sh.entity.device.microwave.state;

import dev.mikita.sh.core.SHSystem;
import dev.mikita.sh.entity.device.ADevice;
import dev.mikita.sh.entity.device.ADeviceIdleState;

import java.util.logging.Logger;

public class MicrowaveIdleState extends ADeviceIdleState {
    // Logger
    private static final Logger log = Logger.getLogger(MicrowaveIdleState.class.getName());

    public MicrowaveIdleState(ADevice device) {
        super(device);
        this.ELECTRICITY_CONSUMPTION = 1.28;

        log.info(String.format("Microwave is not being used now [%s]",
                SHSystem.getInstance().getSimulation().getFormattedTime()));
    }

    @Override
    public void update(long time) {
        if (device.getTime() > 10L * 1000000000L) {
            device.changeState(new MicrowaveBrokenState(device));
        }

        device.setTime(device.getTime() + time);
        this.time += time;

        // Consumption
        device.setCurrentElectricityConsumption(device.getCurrentElectricityConsumption()
                + (ELECTRICITY_CONSUMPTION / 3600F * 1000000000) * time);
    }
}
