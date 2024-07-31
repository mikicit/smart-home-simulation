package dev.mikita.sh.entity.device.microwave.state;

import dev.mikita.sh.core.SHSystem;
import dev.mikita.sh.entity.device.ADevice;
import dev.mikita.sh.entity.device.ADeviceUsingState;

import java.util.logging.Logger;

/**
 * Class representing the Microwave using state.
 */
public class MicrowaveUsingState extends ADeviceUsingState {
    // Logger
    private static final Logger log = Logger.getLogger(MicrowaveUsingState.class.getName());

    /**
     * Instantiates a new Microwave using state.
     *
     * @param device the device
     */
    public MicrowaveUsingState(ADevice device) {
        super(device);
        this.ELECTRICITY_CONSUMPTION = 3.6;

        log.info(String.format("Microwave in room \"%s\" is being used now [%s]",
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
            device.changeState(new MicrowaveBrokenState(device));
        }

        this.time += time;
        device.setTime(device.getTime() + time);

        // Consumption
        device.setCurrentElectricityConsumption(device.getCurrentElectricityConsumption()
                + (ELECTRICITY_CONSUMPTION / (3600D * 1000000000)) * time);
    }
}
