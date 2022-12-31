package dev.mikita.sh.entity.device.fridge.state;

import dev.mikita.sh.core.SHSystem;
import dev.mikita.sh.entity.device.ADevice;
import dev.mikita.sh.entity.device.ADeviceUsingState;

import java.util.logging.Logger;

/**
 * The type Fridge using state.
 */
public class FridgeUsingState extends ADeviceUsingState {
    // Logger
    private static final Logger log = Logger.getLogger(FridgeUsingState.class.getName());

    /**
     * Instantiates a new Fridge using state.
     *
     * @param device the device
     */
    public FridgeUsingState(ADevice device) {
        super(device);
        this.ELECTRICITY_CONSUMPTION = 5;

        // Logging
        log.info(String.format("Fridge in room \"%s\" is being used now [%s]",
                device.getRoom().getName(),
                SHSystem.getInstance().getSimulation().getFormattedTime()));
    }

    @Override
    public void update(long time) {
        // Wear out ime
        if (device.getTime() > device.getOperatingTimeInHours() * 3600L * 1000000000L) {
            device.changeState(new FridgeBrokenState(device));
        }

        this.time += time;
        device.setTime(device.getTime() + time);

        // Consumption
        device.setCurrentElectricityConsumption(device.getCurrentElectricityConsumption()
                + (ELECTRICITY_CONSUMPTION / (3600D * 1000000000L)) * time);
    }
}
