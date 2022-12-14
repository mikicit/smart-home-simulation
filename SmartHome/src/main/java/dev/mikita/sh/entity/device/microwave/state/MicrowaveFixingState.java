package dev.mikita.sh.entity.device.microwave.state;

import dev.mikita.sh.entity.device.ADevice;
import dev.mikita.sh.entity.device.ADeviceState;

public class MicrowaveFixingState extends ADeviceState {
    public MicrowaveFixingState(ADevice device) {
        super(device);
    }

    @Override
    public void update(long time) {
        this.time += time;
    }
}
