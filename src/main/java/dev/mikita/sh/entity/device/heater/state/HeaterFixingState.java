package dev.mikita.sh.entity.device.heater.state;

import dev.mikita.sh.core.SHSystem;
import dev.mikita.sh.entity.device.ADevice;
import dev.mikita.sh.entity.device.ADeviceFixingState;

import java.util.logging.Logger;

/**
 * Class representing the Heater fixing state.
 */
public class HeaterFixingState extends ADeviceFixingState {
    // Logger
    private static final Logger log = Logger.getLogger(HeaterFixingState.class.getName());

    /**
     * Instantiates a new Heater fixing state.
     *
     * @param device the device
     */
    public HeaterFixingState(ADevice device) {
        super(device);

        // Logging
        log.info(String.format("Heater in room \"%s\" is being fixed now [%s]",
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
