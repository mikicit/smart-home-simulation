package dev.mikita.sh.entity.device.washingMachine.state;

import dev.mikita.sh.core.SHSystem;
import dev.mikita.sh.entity.device.ADevice;
import dev.mikita.sh.entity.device.ADeviceIdleState;
import dev.mikita.sh.entity.device.ADeviceState;

import java.util.logging.Logger;

public class WashingMachineIdleState extends ADeviceIdleState {
    // Logger
    private static final Logger log = Logger.getLogger(WashingMachineIdleState.class.getName());

    public WashingMachineIdleState(ADevice device) {
        super(device);
        this.ELECTRICITY_CONSUMPTION = 1.28;

        log.info(String.format("Washing machine is not being used now [%s]",
                SHSystem.getInstance().getTimer().getFormattedTime()));
    }

    @Override
    public void update(long time) {
        if (device.getTime() > 10L * 1000000000L) {
            device.changeState(new WashingMachineBrokenState(device));
        }

        device.setTime(device.getTime() + time);
        this.time += time;

        // Consumption
        device.setCurrentElectricityConsumption(device.getCurrentElectricityConsumption()
                + (ELECTRICITY_CONSUMPTION / 3600F * 1000000000) * time);
    }
}
