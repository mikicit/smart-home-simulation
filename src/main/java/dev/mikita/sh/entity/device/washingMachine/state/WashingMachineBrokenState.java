package dev.mikita.sh.entity.device.washingMachine.state;

import dev.mikita.sh.core.SHSystem;
import dev.mikita.sh.entity.device.ADevice;
import dev.mikita.sh.entity.device.ADeviceBrokenState;
import dev.mikita.sh.event.DeviceIsBrokenEvent;
import lombok.extern.slf4j.Slf4j;

/**
 * Class representing the Washing machine broken state.
 */
@Slf4j
public class WashingMachineBrokenState extends ADeviceBrokenState {
    /**
     * Instantiates a new Washing machine broken state.
     *
     * @param device the device
     */
    public WashingMachineBrokenState(ADevice device) {
        super(device);
        SHSystem.getInstance().getEventDispatcher().dispatchEvent(new DeviceIsBrokenEvent(device, device.getRoom()), "global");

        log.info(String.format("Washing machine in room \"%s\" is broken now [%s]",
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
