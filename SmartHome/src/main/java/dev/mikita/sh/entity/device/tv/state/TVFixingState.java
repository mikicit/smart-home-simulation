package dev.mikita.sh.entity.device.tv.state;

import dev.mikita.sh.core.SHSystem;
import dev.mikita.sh.entity.device.ADevice;
import dev.mikita.sh.entity.device.ADeviceFixingState;

import java.util.logging.Logger;

public class TVFixingState extends ADeviceFixingState {
    // Logger
    private static final Logger log = Logger.getLogger(TVFixingState.class.getName());

    public TVFixingState(ADevice device) {
        super(device);

        // Logging
        log.info(String.format("TV in room \"%s\" is fixing now [%s]",
                device.getRoom().getName(),
                SHSystem.getInstance().getSimulation().getFormattedTime()));
    }

    @Override
    public void update(long time) {
        this.time += time;
    }
}
