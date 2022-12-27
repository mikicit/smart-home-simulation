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

        log.info(String.format("Washing machine in room \"%s\" is being used now [%s]",
                device.getRoom().getName(),
                SHSystem.getInstance().getSimulation().getFormattedTime()));
    }

    @Override
    public void update(long time) {
        // Wear out time
        if (device.getTime() > device.getOperatingTimeInHours() * 3600D * 1000000000L) {
            device.changeState(new WashingMachineBrokenState(device));
        }

        this.time += time;
        device.setTime(device.getTime() + time);

        // Consumption
        device.setCurrentElectricityConsumption(device.getCurrentElectricityConsumption()
                + (ELECTRICITY_CONSUMPTION / 3600D * 1000000000) * this.time);
        device.setCurrentWaterConsumption(device.getCurrentWaterConsumption()
                + (WATER_CONSUMPTION / 3600D * 1000000000) * this.time);
    }
}
