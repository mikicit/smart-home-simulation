package dev.mikita.sh.entity.device.tv.state;

import dev.mikita.sh.core.SHSystem;
import dev.mikita.sh.entity.device.ADevice;
import dev.mikita.sh.entity.device.ADeviceUsingState;
import lombok.extern.slf4j.Slf4j;

/**
 * Class representing the TV using state.
 */
@Slf4j
public class TVUsingState extends ADeviceUsingState {
    /**
     * Instantiates a new Tv using state.
     *
     * @param device the device
     */
    public TVUsingState(ADevice device) {
        super(device);
        this.ELECTRICITY_CONSUMPTION = 1.6;

        // Logging
        log.info(String.format("TV in room \"%s\" is being used now [%s]",
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
            device.changeState(new TVBrokenState(device));
        }

        this.time += time;
        device.setTime(device.getTime() + time);

        // Consumption
        device.setCurrentElectricityConsumption(device.getCurrentElectricityConsumption()
                 + (ELECTRICITY_CONSUMPTION / (3600D * 1000000000)) * time);
    }
}
