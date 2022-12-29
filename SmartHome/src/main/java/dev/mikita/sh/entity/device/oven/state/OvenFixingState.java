package dev.mikita.sh.entity.device.oven.state;

import dev.mikita.sh.core.SHSystem;
import dev.mikita.sh.entity.device.ADevice;
import dev.mikita.sh.entity.device.ADeviceFixingState;

import java.util.logging.Logger;

public class OvenFixingState extends ADeviceFixingState {
    // Logger
    private static final Logger log = Logger.getLogger(OvenFixingState.class.getName());

    public OvenFixingState(ADevice device) {
        super(device);

        // Logging
        log.info(String.format("Oven in room \"%s\" is being fixed now [%s]",
                device.getRoom().getName(),
                SHSystem.getInstance().getSimulation().getFormattedTime()));
    }

    @Override
    public void update(long time) {
        this.time += time;
    }
}
