package dev.mikita.sh.entity.device.washingMachine.state;

import dev.mikita.sh.core.SHSystem;
import dev.mikita.sh.entity.device.ADevice;
import dev.mikita.sh.entity.device.ADeviceUsingState;

import java.util.logging.Logger;

public class WashingMachineUsingState extends ADeviceUsingState {
    // Logger
    private static final Logger log = Logger.getLogger(WashingMachineUsingState.class.getName());

    public WashingMachineUsingState(ADevice device) {
        super(device);
        this.ELECTRICITY_CONSUMPTION = 1.28;
        this.WATER_CONSUMPTION = 60;

        log.info(String.format("Washing machine is being used now [%s]",
                SHSystem.getInstance().getSimulation().getFormattedTime()));
    }

    @Override
    public void update(long time) {
        if (device.getTime() > 5000) {
            device.changeState(new WashingMachineBrokenState(device));
        }

        device.setTime(device.getTime() + time);
        this.time += time;

        // Consumption
        device.setCurrentElectricityConsumption(device.getCurrentElectricityConsumption()
                + (ELECTRICITY_CONSUMPTION / 3600F * 1000000000) * time);
        device.setCurrentWaterConsumption(device.getCurrentWaterConsumption()
                + (WATER_CONSUMPTION / 3600F * 1000000000) * time);
    }
}
