package dev.mikita.sh.entity.device.washingMachine.state;

import dev.mikita.sh.entity.device.ADevice;
import dev.mikita.sh.entity.device.ADeviceState;

public class WashingMachineUsingState extends ADeviceState {
    public WashingMachineUsingState(ADevice device) {
        super(device);
        this.ELECTRICITY_CONSUMPTION = 1.28;
        this.WATER_CONSUMPTION = 60;
    }

    @Override
    public void update(long time) {
        if (device.getTime() > 5000) {
            device.changeState(new WashingMachineBrokenState(device));
        }

        device.setTime(device.getTime() + time);
        this.time += time;
    }
}
