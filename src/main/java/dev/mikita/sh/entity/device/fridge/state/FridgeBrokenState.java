package dev.mikita.sh.entity.device.fridge.state;

import dev.mikita.sh.core.SHSystem;
import dev.mikita.sh.entity.device.ADevice;
import dev.mikita.sh.entity.device.ADeviceBrokenState;
import dev.mikita.sh.event.DeviceIsBrokenEvent;
import lombok.extern.slf4j.Slf4j;

/**
 * Class representing the Fridge broken state.
 */
@Slf4j
public class FridgeBrokenState extends ADeviceBrokenState {
    /**
     * Instantiates a new Fridge broken state.
     *
     * @param device the device
     */
    public FridgeBrokenState(ADevice device) {
        super(device);

        // Event
        SHSystem.getInstance().getEventDispatcher().dispatchEvent(new DeviceIsBrokenEvent(device, device.getRoom()), "global");

        // Logging
        log.info(String.format("Fridge in room \"%s\" is broken now [%s]",
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
