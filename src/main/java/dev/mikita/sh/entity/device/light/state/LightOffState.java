package dev.mikita.sh.entity.device.light.state;

import dev.mikita.sh.core.SHSystem;
import dev.mikita.sh.entity.device.ADevice;
import dev.mikita.sh.entity.device.ADeviceOffState;
import lombok.extern.slf4j.Slf4j;

/**
 * Class representing the Light off state.
 */
@Slf4j
public class LightOffState extends ADeviceOffState {
    /**
     * Instantiates a new Light off state.
     *
     * @param device the device
     */
    public LightOffState(ADevice device) {
        super(device);

        // Logging
        log.info(String.format("Light in room \"%s\" was turned off now[%s]",
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
