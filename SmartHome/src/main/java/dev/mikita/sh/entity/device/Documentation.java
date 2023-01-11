package dev.mikita.sh.entity.device;
import dev.mikita.sh.core.SHSystem;

import java.util.logging.Logger;

/**
 * Class representing device's documentation.
 */
public class Documentation {
    // Logger
    private static final Logger log = Logger.getLogger(Documentation.class.getName());

    private final ADevice device;
    private final double fixingTimeInHours;

    /**
     * Instantiates a new Documentation.
     *
     * @param device            the device
     * @param fixingTimeInHours the fixing time in hours
     */
    public Documentation(ADevice device, double fixingTimeInHours) {
        this.device = device;
        this.fixingTimeInHours = fixingTimeInHours;
    }

    /**
     * Just logging...
     *
     * @param device that is being fixed
     */
    public void fixDevice(ADevice device) {
        log.info(String.format("Reading manual for the device \"%s\"... [%s]",
                device.getName(),
                SHSystem.getInstance().getSimulation().getFormattedTime()));
    }
}
