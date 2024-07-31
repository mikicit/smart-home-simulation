package dev.mikita.sh.entity.device.tv.state;

import dev.mikita.sh.core.SHSystem;
import dev.mikita.sh.entity.device.ADevice;
import dev.mikita.sh.entity.device.ADeviceOffState;

import java.util.logging.Logger;

/**
 * Class representing the TV off state.
 */
public class TVOffState extends ADeviceOffState {
    // Logger
    private static final Logger log = Logger.getLogger(TVOffState.class.getName());

    /**
     * Instantiates a new Tv off state.
     *
     * @param device the device
     */
    public TVOffState(ADevice device) {
        super(device);

        // Logging
        log.info(String.format("TV in room \"%s\" was turned off now[%s]",
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
