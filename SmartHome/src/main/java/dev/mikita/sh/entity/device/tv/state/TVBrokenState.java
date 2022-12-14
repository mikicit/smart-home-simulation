package dev.mikita.sh.entity.device.tv.state;

import dev.mikita.sh.core.SHSystem;
import dev.mikita.sh.entity.device.ADevice;
import dev.mikita.sh.entity.device.ADeviceState;
import dev.mikita.sh.event.DeviceIsBroken;

public class TVBrokenState extends ADeviceState {
    public TVBrokenState(ADevice device) {
        super(device);
        SHSystem.getInstance().getEventDispatcher().dispatchEvent(new DeviceIsBroken(device, device.getRoom()), device.getRoom().toString());
    }

    @Override
    public void update(long time) {
        this.time += time;
    }
}
