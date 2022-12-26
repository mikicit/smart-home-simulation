package dev.mikita.sh.entity.device.fridge.state;

import dev.mikita.sh.core.SHSystem;
import dev.mikita.sh.entity.device.ADevice;
import dev.mikita.sh.entity.device.ADeviceIdleState;

import java.util.logging.Logger;

public class FridgeIdleState extends ADeviceIdleState {
    // Logger
    private static final Logger log = Logger.getLogger(FridgeIdleState.class.getName());

    public FridgeIdleState(ADevice device) {
        super(device);
        this.ELECTRICITY_CONSUMPTION = 1.28;

        log.info(String.format("Fridge is not being used now [%s]",
                SHSystem.getInstance().getSimulation().getFormattedTime()));
    }

    @Override
    public void update(long time) {
        this.time += time;
        device.setTime(device.getTime() + time);

        // TODO Ломается сразу, херня со временем, странные значения в глобальном тайме
        if (device.getTime() > device.getOperatingTimeInHours() * 3600L * 1000000000L) {
            device.changeState(new FridgeBrokenState(device));
        }

        // Consumption
        device.setCurrentElectricityConsumption(device.getCurrentElectricityConsumption()
                + (ELECTRICITY_CONSUMPTION / (3600L * 1000000000L)) * time);

    }
}
