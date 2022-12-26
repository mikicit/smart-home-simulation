package dev.mikita.sh.entity.device.washingMachine.state;

import dev.mikita.sh.core.SHSystem;
import dev.mikita.sh.core.event.IEventSource;
import dev.mikita.sh.entity.device.ADevice;
import dev.mikita.sh.entity.device.ADeviceBrokenState;
import dev.mikita.sh.event.DeviceIsBrokenEvent;

import java.util.logging.Logger;

public class WashingMachineBrokenState extends ADeviceBrokenState {
    // Logger
    private static final Logger log = Logger.getLogger(WashingMachineBrokenState.class.getName());

    public WashingMachineBrokenState(ADevice device) {
        super(device);
        SHSystem.getInstance().getEventDispatcher().dispatchEvent(new DeviceIsBrokenEvent((IEventSource) device, device.getRoom()), device.getRoom().toString());

        log.info(String.format("Washing machine in room \"%s\" is broken now [%s]",
                device.getRoom().getName(),
                SHSystem.getInstance().getSimulation().getFormattedTime()));
    }

    @Override
    public void update(long time) {
        this.time += time;
    }
}
