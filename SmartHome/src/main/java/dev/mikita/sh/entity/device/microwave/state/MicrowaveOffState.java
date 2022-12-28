package dev.mikita.sh.entity.device.microwave.state;

import dev.mikita.sh.core.SHSystem;
import dev.mikita.sh.entity.device.ADevice;
import dev.mikita.sh.entity.device.ADeviceOffState;

import java.util.logging.Logger;

public class MicrowaveOffState extends ADeviceOffState {
    // Logger
    private static final Logger log = Logger.getLogger(MicrowaveOffState.class.getName());

    public MicrowaveOffState(ADevice device) {
        super(device);

        // Logging
        log.info(String.format("Microwave in room \"%s\" was turned off now[%s]",
                device.getRoom().getName(),
                SHSystem.getInstance().getSimulation().getFormattedTime()));
    }

    @Override
    public void update(long time) {
        this.time += time;
    }
}
