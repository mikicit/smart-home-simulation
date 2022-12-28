package dev.mikita.sh.entity.device.microwave.state;

import dev.mikita.sh.core.SHSystem;
import dev.mikita.sh.entity.device.ADevice;
import dev.mikita.sh.entity.device.ADeviceBrokenState;
import dev.mikita.sh.event.DeviceIsBrokenEvent;

import java.util.logging.Logger;

public class MicrowaveBrokenState extends ADeviceBrokenState {
    // Logger
    private static final Logger log = Logger.getLogger(MicrowaveBrokenState.class.getName());

    public MicrowaveBrokenState(ADevice device) {
        super(device);
        SHSystem.getInstance().getEventDispatcher().dispatchEvent(new DeviceIsBrokenEvent(device, device.getRoom()), "global");

        // Logging
        log.info(String.format("Microwave in room \"%s\" is broken now [%s]",
                device.getRoom().getName(),
                SHSystem.getInstance().getSimulation().getFormattedTime()));
    }

    @Override
    public void update(long time) {
        this.time += time;
    }
}
