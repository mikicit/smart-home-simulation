package dev.mikita.sh.entity.device.light.state;

import dev.mikita.sh.core.SHSystem;
import dev.mikita.sh.core.event.IEventSource;
import dev.mikita.sh.entity.device.ADevice;
import dev.mikita.sh.entity.device.ADeviceBrokenState;
import dev.mikita.sh.event.DeviceIsBrokenEvent;

import java.util.logging.Logger;

public class LightBrokenState extends ADeviceBrokenState {
    // Logger
    private static final Logger log = Logger.getLogger(LightBrokenState.class.getName());

    public LightBrokenState(ADevice device) {
        super(device);
        SHSystem.getInstance().getEventDispatcher().dispatchEvent(new DeviceIsBrokenEvent((IEventSource) device, device.getRoom()), device.getRoom().toString());

        log.info(String.format("Light in room \"%s\" is broken now [%s]",
                device.getRoom().getName(),
                SHSystem.getInstance().getSimulation().getFormattedTime()));
    }

    @Override
    public void update(long time) {
        this.time += time;
    }
}
