package dev.mikita.sh.entity.device.heater.state;

import dev.mikita.sh.core.SHSystem;
import dev.mikita.sh.entity.device.ADevice;
import dev.mikita.sh.entity.device.ADeviceOffState;

import java.util.logging.Logger;

/**
 * Class representing the Heater off state.
 */
public class HeaterOffState extends ADeviceOffState {
    // Logger
    private static final Logger log = Logger.getLogger(HeaterOffState.class.getName());

    /**
     * Instantiates a new Heater off state.
     *
     * @param device the device
     */
    public HeaterOffState(ADevice device) {
        super(device);

        // Logging
        log.info(String.format("Heater in room \"%s\" was turned off now[%s]",
                device.getRoom().getName(),
                SHSystem.getInstance().getSimulation().getFormattedTime()));
    }

    /**
     * Update.
     * @param time the time
     */
    @Override
    public void update(long time) {
        this.time += time;
    }
}
