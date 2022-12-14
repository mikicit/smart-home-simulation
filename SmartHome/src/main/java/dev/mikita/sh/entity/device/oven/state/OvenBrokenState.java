package dev.mikita.sh.entity.device.oven.state;

import dev.mikita.sh.core.SHSystem;
import dev.mikita.sh.core.event.IEventSource;
import dev.mikita.sh.entity.device.ADevice;
import dev.mikita.sh.entity.device.ADeviceState;
import dev.mikita.sh.event.DeviceIsBroken;

public class OvenBrokenState extends ADeviceState {
    public OvenBrokenState(ADevice device) {
        super(device);
        SHSystem.getInstance().getEventDispatcher().dispatchEvent(new DeviceIsBroken(device, device.getRoom()), device.getRoom().toString());
    }

    @Override
    public void update(long time) {
        this.time += time;
    }
}
