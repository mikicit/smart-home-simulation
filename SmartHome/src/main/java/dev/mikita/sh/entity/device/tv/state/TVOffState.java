package dev.mikita.sh.entity.device.tv.state;

import dev.mikita.sh.core.SHSystem;
import dev.mikita.sh.entity.device.ADevice;
import dev.mikita.sh.entity.device.ADeviceOffState;

import java.util.logging.Logger;

/**
 * The type Tv off state.
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

    @Override
    public void update(long time) {
        this.time += time;
    }
}
