package dev.mikita.sh.entity.device.light.state;

import dev.mikita.sh.core.SHSystem;
import dev.mikita.sh.entity.device.ADevice;
import dev.mikita.sh.entity.device.ADeviceIdleState;
import lombok.extern.slf4j.Slf4j;

/**
 * Class representing the Light idle state.
 */
@Slf4j
public class LightIdleState extends ADeviceIdleState {
    /**
     * Instantiates a new Light idle state.
     *
     * @param device the device
     */
    public LightIdleState(ADevice device) {
        super(device);
        this.ELECTRICITY_CONSUMPTION = 0.1;

        // Logging
        log.info(String.format("Light in room \"%s\" is not working now [%s]",
                device.getRoom().getName(),
                SHSystem.getInstance().getSimulation().getFormattedTime()));
    }

    /**
     * Update.
     * @param time the time
     */
    @Override
    public void update(long time) {
        // Wear out time
        if (device.getTime() > device.getOperatingTimeInHours() * 3600L * 1000000000L) {
            device.changeState(new LightBrokenState(device));
        }

        this.time += time;
        device.setTime(device.getTime() + time);

        // Consumption
        device.setCurrentElectricityConsumption(device.getCurrentElectricityConsumption()
                + (ELECTRICITY_CONSUMPTION / (3600D * 1000000000)) * time);
    }
}
