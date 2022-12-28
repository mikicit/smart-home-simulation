package dev.mikita.sh.entity.device.light.state;

import dev.mikita.sh.core.SHSystem;
import dev.mikita.sh.entity.device.ADevice;
import dev.mikita.sh.entity.device.ADeviceOffState;

import java.util.logging.Logger;

public class LightOffState extends ADeviceOffState {
    // Logger
    private static final Logger log = Logger.getLogger(LightOffState.class.getName());

    public LightOffState(ADevice device) {
        super(device);

        // Logging
        log.info(String.format("Light in room \"%s\" was turned off now[%s]",
                device.getRoom().getName(),
                SHSystem.getInstance().getSimulation().getFormattedTime()));
    }

    @Override
    public void update(long time) {
        this.time += time;
    }
}
