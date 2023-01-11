package dev.mikita.sh.entity.device.microwave.state;

import dev.mikita.sh.core.SHSystem;
import dev.mikita.sh.entity.device.ADevice;
import dev.mikita.sh.entity.device.ADeviceFixingState;

import java.util.logging.Logger;

/**
 * Class representing the Microwave fixing state.
 */
public class MicrowaveFixingState extends ADeviceFixingState {
    // Logger
    private static final Logger log = Logger.getLogger(MicrowaveFixingState.class.getName());

    /**
     * Instantiates a new Microwave fixing state.
     *
     * @param device the device
     */
    public MicrowaveFixingState(ADevice device) {
        super(device);

        // Logging
        log.info(String.format("Microwave in room \"%s\" is being fixed now [%s]",
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
