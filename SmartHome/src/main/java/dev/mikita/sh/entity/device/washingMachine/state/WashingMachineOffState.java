package dev.mikita.sh.entity.device.washingMachine.state;

import dev.mikita.sh.core.SHSystem;
import dev.mikita.sh.entity.device.ADevice;
import dev.mikita.sh.entity.device.ADeviceOffState;
import java.util.logging.Logger;

/**
 * Class representing the Washing machine off state.
 */
public class WashingMachineOffState extends ADeviceOffState {
    // Logger
    private static final Logger log = Logger.getLogger(WashingMachineOffState.class.getName());

    /**
     * Instantiates a new Washing machine off state.
     *
     * @param device the device
     */
    public WashingMachineOffState(ADevice device) {
        super(device);

        // Logging
        log.info(String.format("Washing machine in room \"%s\" was turned off now[%s]",
                device.getRoom().getName(),
                SHSystem.getInstance().getSimulation().getFormattedTime()));
    }

    /**
     * Update.
     * @param time the time
     */
    @Override
    public void update(long time) {
        this.time += time;
    }
}
