package dev.mikita.sh.entity.device.washingMachine.state;

import dev.mikita.sh.entity.device.ADevice;
import dev.mikita.sh.entity.device.ADeviceState;

public class WashingMachineIdleState extends ADeviceState  {
    public WashingMachineIdleState(ADevice device) {
        super(device);
        this.ELECTRICITY_CONSUMPTION = 1.28;
    }

    @Override
    public void update(long time) {
        if (device.getTime() > 10L * 1000000000L) {
            device.changeState(new WashingMachineBrokenState(device));
        }

        device.setTime(device.getTime() + time);
        this.time += time;
    }
}
