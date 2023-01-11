package dev.mikita.sh.entity.device.airConditioner.state;

import dev.mikita.sh.core.SHSystem;
import dev.mikita.sh.entity.device.ADevice;
import dev.mikita.sh.entity.device.ADeviceBrokenState;
import dev.mikita.sh.event.DeviceIsBrokenEvent;
import java.util.logging.Logger;

/**
 * Class representing the Air conditioner broken state.
 */
public class AirConditionerBrokenState extends ADeviceBrokenState {
    // Logger
    private static final Logger log = Logger.getLogger(AirConditionerBrokenState.class.getName());

    /**
     * Instantiates a new Air conditioner broken state.
     *
     * @param device the device
     */
    public AirConditionerBrokenState(ADevice device) {
        super(device);
        SHSystem.getInstance().getEventDispatcher().dispatchEvent(new DeviceIsBrokenEvent(device, device.getRoom()), "global");

        // Logging
        log.info(String.format("Air conditioner in room \"%s\" is broken now [%s]",
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
