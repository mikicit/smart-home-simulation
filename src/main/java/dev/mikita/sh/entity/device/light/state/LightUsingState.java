package dev.mikita.sh.entity.device.light.state;

import dev.mikita.sh.core.SHSystem;
import dev.mikita.sh.entity.device.ADevice;
import dev.mikita.sh.entity.device.ADeviceUsingState;

import java.util.logging.Logger;

/**
 * Class representing the Light using state.
 */
public class LightUsingState extends ADeviceUsingState {
    // Logger
    private static final Logger log = Logger.getLogger(LightUsingState.class.getName());

    /**
     * Instantiates a new Light using state.
     *
     * @param device the device
     */
    public LightUsingState(ADevice device) {
        super(device);
        this.ELECTRICITY_CONSUMPTION = 0.4;

        log.info(String.format("Light in room \"%s\" is working now [%s]",
                device.getRoom().getName(),
                SHSystem.getInstance().getSimulation().getFormattedTime()));
    }

    /**
     * Update.
     * @param time the time
     */
    @Override
    public void update(long time) {
        // Wear out ime
        if (device.getTime() > device.getOperatingTimeInHours() * 3600L * 1000000000L) {
            device.changeState(new LightBrokenState(device));
        }

        this.time += time;
        device.setTime(device.getTime() + time);

        // Consumption
        device.setCurrentElectricityConsumption(device.getCurrentElectricityConsumption()
                + (ELECTRICITY_CONSUMPTION / (3600D * 1000000000)) * time);
    }
}
