package dev.mikita.sh.entity.device.light.state;

import dev.mikita.sh.core.SHSystem;
import dev.mikita.sh.entity.device.ADevice;
import dev.mikita.sh.entity.device.ADeviceFixingState;

import java.util.logging.Logger;

public class LightFixingState extends ADeviceFixingState {
    // Logger
    private static final Logger log = Logger.getLogger(LightFixingState.class.getName());

    public LightFixingState(ADevice device) {
        super(device);

        // Logging
        log.info(String.format("Light in room \"%s\" is being fixed now [%s]",
                device.getRoom().getName(),
                SHSystem.getInstance().getSimulation().getFormattedTime()));
    }

    @Override
    public void update(long time) {
        this.time += time;
    }
}
