package dev.mikita.sh.entity.device.microwave.state;

import dev.mikita.sh.core.SHSystem;
import dev.mikita.sh.entity.device.ADevice;
import dev.mikita.sh.entity.device.ADeviceOffState;
import lombok.extern.slf4j.Slf4j;

/**
 * Class representing the Microwave off state.
 */
@Slf4j
public class MicrowaveOffState extends ADeviceOffState {
    /**
     * Instantiates a new Microwave off state.
     *
     * @param device the device
     */
    public MicrowaveOffState(ADevice device) {
        super(device);

        // Logging
        log.info(String.format("Microwave in room \"%s\" was turned off now[%s]",
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
