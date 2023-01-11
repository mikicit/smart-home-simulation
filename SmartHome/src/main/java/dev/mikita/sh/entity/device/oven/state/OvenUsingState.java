package dev.mikita.sh.entity.device.oven.state;

import dev.mikita.sh.core.SHSystem;
import dev.mikita.sh.entity.device.ADevice;
import dev.mikita.sh.entity.device.ADeviceUsingState;

import java.util.logging.Logger;

/**
 * Class representing the Oven using state.
 */
public class OvenUsingState extends ADeviceUsingState {
    // Logger
    private static final Logger log = Logger.getLogger(OvenUsingState.class.getName());

    /**
     * Instantiates a new Oven using state.
     *
     * @param device the device
     */
    public OvenUsingState(ADevice device) {
        super(device);
        this.ELECTRICITY_CONSUMPTION = 1.20;
        this.GAS_CONSUMPTION = 2.4;

        log.info(String.format("Oven in room \"%s\" is being used now [%s]",
                device.getRoom().getName(),
                SHSystem.getInstance().getSimulation().getFormattedTime()));
    }

    /**
     * Update.
     * @param time the time
     */
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
                + (ELECTRICITY_CONSUMPTION / (3600D * 1000000000)) * time);
        device.setCurrentGasConsumption(device.getCurrentGasConsumption()
                + (GAS_CONSUMPTION / (3600D * 1000000000)) * time);
    }
}
