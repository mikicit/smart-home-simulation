package dev.mikita.sh.entity.device.microwave.state;

import dev.mikita.sh.core.SHSystem;
import dev.mikita.sh.entity.device.ADevice;
import dev.mikita.sh.entity.device.ADeviceFixingState;

import java.util.logging.Logger;

public class MicrowaveFixingState extends ADeviceFixingState {
    // Logger
    private static final Logger log = Logger.getLogger(MicrowaveFixingState.class.getName());

    public MicrowaveFixingState(ADevice device) {
        super(device);

        // Logging
        log.info(String.format("Microwave in room \"%s\" is being fixed now [%s]",
                device.getRoom().getName(),
                SHSystem.getInstance().getSimulation().getFormattedTime()));
    }

    @Override
    public void update(long time) {
        this.time += time;
    }
}
