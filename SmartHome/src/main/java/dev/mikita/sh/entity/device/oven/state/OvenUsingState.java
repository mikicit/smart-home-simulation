package dev.mikita.sh.entity.device.oven.state;

import dev.mikita.sh.core.SHSystem;
import dev.mikita.sh.entity.device.ADevice;
import dev.mikita.sh.entity.device.ADeviceUsingState;

import java.util.logging.Logger;

public class OvenUsingState extends ADeviceUsingState {
    // Logger
    private static final Logger log = Logger.getLogger(OvenUsingState.class.getName());

    public OvenUsingState(ADevice device) {
        super(device);
        this.ELECTRICITY_CONSUMPTION = 1;
        this.GAS_CONSUMPTION = 2;

        log.info(String.format("Oven is being used now [%s]",
                SHSystem.getInstance().getSimulation().getFormattedTime()));
    }

    @Override
    public void update(long time) {
        if (device.getTime() > 5000) {
            device.changeState(new OvenBrokenState(device));
        }

        device.setTime(device.getTime() + time);
        this.time += time;

        // Consumption
        device.setCurrentElectricityConsumption(device.getCurrentElectricityConsumption()
                + (ELECTRICITY_CONSUMPTION / 3600F * 1000000000) * this.time);
        device.setCurrentGasConsumption(device.getCurrentGasConsumption()
                + (GAS_CONSUMPTION / 3600F * 1000000000) * this.time);
    }
}
