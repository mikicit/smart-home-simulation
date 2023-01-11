package dev.mikita.sh.entity.device.fridge.state;

import dev.mikita.sh.core.SHSystem;
import dev.mikita.sh.entity.device.ADevice;
import dev.mikita.sh.entity.device.ADeviceOffState;

import java.util.logging.Logger;

/**
 * Class representing the Fridge off state.
 */
public class FridgeOffState extends ADeviceOffState {
    // Logger
    private static final Logger log = Logger.getLogger(FridgeOffState.class.getName());

    /**
     * Instantiates a new Fridge off state.
     *
     * @param device the device
     */
    public FridgeOffState(ADevice device) {
        super(device);

        // Logging
        log.info(String.format("Fridge in room \"%s\" was turned off now[%s]",
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
