package dev.mikita.sh.entity.device.heater.state;

import dev.mikita.sh.core.SHSystem;
import dev.mikita.sh.entity.device.ADevice;
import dev.mikita.sh.entity.device.ADeviceFixingState;

import java.util.logging.Logger;

public class HeaterFixingState extends ADeviceFixingState {
    // Logger
    private static final Logger log = Logger.getLogger(HeaterFixingState.class.getName());

    public HeaterFixingState(ADevice device) {
        super(device);

        // Logging
        log.info(String.format("Heater in room \"%s\" is fixing now [%s]",
                device.getRoom().getName(),
                SHSystem.getInstance().getSimulation().getFormattedTime()));
    }

    @Override
    public void update(long time) {
        this.time += time;
    }
}
