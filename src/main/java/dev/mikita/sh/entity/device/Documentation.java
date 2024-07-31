package dev.mikita.sh.entity.device;

import dev.mikita.sh.core.SHSystem;
import lombok.extern.slf4j.Slf4j;

/**
 * Class representing device's documentation.
 */
@Slf4j
public class Documentation {
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
