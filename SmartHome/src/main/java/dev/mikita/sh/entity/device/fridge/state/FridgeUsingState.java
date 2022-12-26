package dev.mikita.sh.entity.device.fridge.state;

import dev.mikita.sh.core.SHSystem;
import dev.mikita.sh.entity.device.ADevice;
import dev.mikita.sh.entity.device.ADeviceUsingState;

import java.util.logging.Logger;

public class FridgeUsingState extends ADeviceUsingState {
    // Logger
    private static final Logger log = Logger.getLogger(FridgeUsingState.class.getName());

    public FridgeUsingState(ADevice device) {
        super(device);
        this.ELECTRICITY_CONSUMPTION = 1.28;

        log.info(String.format("Fridge is being used now [%s]",
                SHSystem.getInstance().getTimer().getFormattedTime()));
    }

    @Override
    public void update(long time) {
        this.time += time;
        device.setTime(device.getTime() + time);

        if (device.getTime() > device.getOperatingTimeInHours() * 3600F * 1000000000) {
            device.changeState(new FridgeBrokenState(device));
        }

        // Consumption
        device.setCurrentElectricityConsumption(device.getCurrentElectricityConsumption()
                + (ELECTRICITY_CONSUMPTION / 3600F * 1000000000) * time);
    }
}
