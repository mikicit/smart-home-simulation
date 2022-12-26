package dev.mikita.sh.entity.device.fridge.state;

import dev.mikita.sh.core.SHSystem;
import dev.mikita.sh.core.event.IEventSource;
import dev.mikita.sh.entity.device.ADevice;
import dev.mikita.sh.entity.device.ADeviceBrokenState;
import dev.mikita.sh.event.DeviceIsBrokenEvent;

import java.util.logging.Logger;

public class FridgeBrokenState extends ADeviceBrokenState {
    // Logger
    private static final Logger log = Logger.getLogger(FridgeBrokenState.class.getName());

    public FridgeBrokenState(ADevice device) {
        super(device);

        // Event
        SHSystem.getInstance().getEventDispatcher().dispatchEvent(new DeviceIsBrokenEvent((IEventSource) device, device.getRoom()), device.getRoom().toString());

        // Logging
        log.info(String.format("Fridge in room \"%s\" is broken now [%s]",
                device.getRoom().getName(),
                SHSystem.getInstance().getSimulation().getFormattedTime()));
    }

    @Override
    public void update(long time) {
        this.time += time;
    }
}
