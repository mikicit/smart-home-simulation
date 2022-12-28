package dev.mikita.sh.entity.device.washingMachine.state;

import dev.mikita.sh.core.SHSystem;
import dev.mikita.sh.entity.device.ADevice;
import dev.mikita.sh.entity.device.ADeviceOffState;
import java.util.logging.Logger;

public class WashingMachineOffState extends ADeviceOffState {
    // Logger
    private static final Logger log = Logger.getLogger(WashingMachineOffState.class.getName());

    public WashingMachineOffState(ADevice device) {
        super(device);

        // Logging
        log.info(String.format("Washing machine in room \"%s\" was turned off now[%s]",
                device.getRoom().getName(),
                SHSystem.getInstance().getSimulation().getFormattedTime()));
    }

    @Override
    public void update(long time) {
        this.time += time;
    }
}
