package dev.mikita.sh.entity.device.heater.state;

import dev.mikita.sh.core.SHSystem;
import dev.mikita.sh.entity.device.ADevice;
import dev.mikita.sh.entity.device.ADeviceIdleState;
import java.util.logging.Logger;

public class HeaterIdleState extends ADeviceIdleState {
    // Logger
    private static final Logger log = Logger.getLogger(HeaterIdleState.class.getName());

    public HeaterIdleState(ADevice device) {
        super(device);
        this.ELECTRICITY_CONSUMPTION = 0.5;

        log.info(String.format("Heater in room \"%s\" is not working now [%s]",
                device.getRoom().getName(),
                SHSystem.getInstance().getSimulation().getFormattedTime()));
    }

    @Override
    public void update(long time) {
        // Wear out ime
        if (device.getTime() > device.getOperatingTimeInHours() * 3600L * 1000000000L) {
            device.changeState(new HeaterBrokenState(device));
        }

        this.time += time;
        device.setTime(device.getTime() + time);

        // Consumption
        device.setCurrentElectricityConsumption(device.getCurrentElectricityConsumption()
                + (ELECTRICITY_CONSUMPTION / (3600L * 1000000000L)) * this.time);
    }
}
