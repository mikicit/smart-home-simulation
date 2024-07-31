package dev.mikita.sh.entity.device.heater.state;

import dev.mikita.sh.core.SHSystem;
import dev.mikita.sh.entity.device.ADevice;
import dev.mikita.sh.entity.device.ADeviceFixingState;
import lombok.extern.slf4j.Slf4j;

/**
 * Class representing the Heater fixing state.
 */
@Slf4j
public class HeaterFixingState extends ADeviceFixingState {
    /**
     * Instantiates a new Heater fixing state.
     *
     * @param device the device
     */
    public HeaterFixingState(ADevice device) {
        super(device);

        // Logging
        log.info(String.format("Heater in room \"%s\" is being fixed now [%s]",
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
