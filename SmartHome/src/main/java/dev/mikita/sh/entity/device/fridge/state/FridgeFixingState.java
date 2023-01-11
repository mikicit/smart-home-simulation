package dev.mikita.sh.entity.device.fridge.state;

import dev.mikita.sh.core.SHSystem;
import dev.mikita.sh.entity.device.ADevice;
import dev.mikita.sh.entity.device.ADeviceFixingState;

import java.util.logging.Logger;

/**
 * Class representing the Fridge fixing state.
 */
public class FridgeFixingState extends ADeviceFixingState {
    // Logger
    private static final Logger log = Logger.getLogger(FridgeFixingState.class.getName());

    /**
     * Instantiates a new Fridge fixing state.
     *
     * @param device the device
     */
    public FridgeFixingState(ADevice device) {
        super(device);

        // Logging
        log.info(String.format("Fridge in room \"%s\" is being fixed now [%s]",
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
