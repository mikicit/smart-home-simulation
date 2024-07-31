package dev.mikita.sh.entity.device.microwave.state;

import dev.mikita.sh.core.SHSystem;
import dev.mikita.sh.entity.device.ADevice;
import dev.mikita.sh.entity.device.ADeviceOffState;

import java.util.logging.Logger;

/**
 * Class representing the Microwave off state.
 */
public class MicrowaveOffState extends ADeviceOffState {
    // Logger
    private static final Logger log = Logger.getLogger(MicrowaveOffState.class.getName());

    /**
     * Instantiates a new Microwave off state.
     *
     * @param device the device
     */
    public MicrowaveOffState(ADevice device) {
        super(device);

        // Logging
        log.info(String.format("Microwave in room \"%s\" was turned off now[%s]",
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
