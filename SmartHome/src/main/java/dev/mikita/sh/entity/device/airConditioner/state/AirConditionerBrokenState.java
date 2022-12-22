package dev.mikita.sh.entity.device.airConditioner.state;

import dev.mikita.sh.core.SHSystem;
import dev.mikita.sh.core.event.IEventSource;
import dev.mikita.sh.entity.device.ADevice;
import dev.mikita.sh.entity.device.ADeviceBrokenState;
import dev.mikita.sh.event.DeviceIsBroken;

import java.util.logging.Logger;

public class AirConditionerBrokenState extends ADeviceBrokenState {
    // Logger
    private static final Logger log = Logger.getLogger(AirConditionerBrokenState.class.getName());

    public AirConditionerBrokenState(ADevice device) {
        super(device);
        SHSystem.getInstance().getEventDispatcher().dispatchEvent(new DeviceIsBroken((IEventSource) device, device.getRoom()), device.getRoom().toString());

        log.info(String.format("Air conditioner in room \"%s\" is broken now [%s]",
                device.getRoom().getName(),
                SHSystem.getInstance().getTimer().getFormattedTime()));
    }

    @Override
    public void update(long time) {
        this.time += time;
    }
}