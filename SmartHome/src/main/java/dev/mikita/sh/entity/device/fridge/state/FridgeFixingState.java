package dev.mikita.sh.entity.device.fridge.state;

import dev.mikita.sh.core.SHSystem;
import dev.mikita.sh.entity.device.ADevice;
import dev.mikita.sh.entity.device.ADeviceFixingState;

import java.util.logging.Logger;

public class FridgeFixingState extends ADeviceFixingState {
    // Logger
    private static final Logger log = Logger.getLogger(FridgeFixingState.class.getName());

    public FridgeFixingState(ADevice device) {
        super(device);

        // Logging
        log.info(String.format("Fridge in room \"%s\" is fixing now [%s]",
                device.getRoom().getName(),
                SHSystem.getInstance().getSimulation().getFormattedTime()));
    }

    @Override
    public void update(long time) {
        this.time += time;
    }
}
