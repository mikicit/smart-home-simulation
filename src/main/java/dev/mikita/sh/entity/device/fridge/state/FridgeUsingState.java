package dev.mikita.sh.entity.device.fridge.state;

import dev.mikita.sh.core.SHSystem;
import dev.mikita.sh.entity.device.ADevice;
import dev.mikita.sh.entity.device.ADeviceUsingState;
import lombok.extern.slf4j.Slf4j;

/**
 * Class representing the Fridge using state.
 */
@Slf4j
public class FridgeUsingState extends ADeviceUsingState {
    /**
     * Instantiates a new Fridge using state.
     *
     * @param device the device
     */
    public FridgeUsingState(ADevice device) {
        super(device);
        this.ELECTRICITY_CONSUMPTION = 5;

        // Logging
        log.info(String.format("Fridge in room \"%s\" is being used now [%s]",
                device.getRoom().getName(),
                SHSystem.getInstance().getSimulation().getFormattedTime()));
    }

    /**
     * Update.
     * @param time the time
     */
    @Override
    public void update(long time) {
        // Wear out ime
        if (device.getTime() > device.getOperatingTimeInHours() * 3600L * 1000000000L) {
            device.changeState(new FridgeBrokenState(device));
        }

        this.time += time;
        device.setTime(device.getTime() + time);

        // Consumption
        device.setCurrentElectricityConsumption(device.getCurrentElectricityConsumption()
                + (ELECTRICITY_CONSUMPTION / (3600D * 1000000000L)) * time);
    }
}
