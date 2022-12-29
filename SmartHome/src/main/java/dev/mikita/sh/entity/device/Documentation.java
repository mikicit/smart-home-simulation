package dev.mikita.sh.entity.device;
import dev.mikita.sh.core.SHSystem;

import java.util.logging.Logger;

public class Documentation {
    // Logger
    private static final Logger log = Logger.getLogger(Documentation.class.getName());

    private final ADevice device;
    private final int fixingTimeInHours;

    public Documentation(ADevice device, int fixingTimeInHours) {
        this.device = device;
        this.fixingTimeInHours = fixingTimeInHours;
    }

    public void fixDevice(ADevice device) {
        log.info(String.format("Reading manual for the device \"%s\"... [%s]",
                device.getName(),
                SHSystem.getInstance().getSimulation().getFormattedTime()));
    }
}
