package dev.mikita.sh.entity.device.heater.state;

import dev.mikita.sh.core.SHSystem;
import dev.mikita.sh.entity.device.ADevice;
import dev.mikita.sh.entity.device.ADeviceUsingState;
import dev.mikita.sh.entity.device.heater.Heater;
import dev.mikita.sh.entity.location.atmosphere.InnerAtmosphere;

import java.util.logging.Logger;

/**
 * Class representing the Heater using state.
 */
public class HeaterUsingState extends ADeviceUsingState {
    // Logger
    private static final Logger log = Logger.getLogger(HeaterUsingState.class.getName());

    /**
     * Instantiates a new Heater using state.
     *
     * @param device the device
     */
    public HeaterUsingState(ADevice device) {
        super(device);
        this.ELECTRICITY_CONSUMPTION = 3;

        log.info(String.format("Heater in room \"%s\" is working now [%s]",
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
            device.changeState(new HeaterBrokenState(device));
        }

        this.time += time;
        device.setTime(device.getTime() + time);

        // Consumption
        device.setCurrentElectricityConsumption(device.getCurrentElectricityConsumption()
                + (ELECTRICITY_CONSUMPTION / (3600D * 1000000000)) * time);

        // Temperature
        double heatingPerHour = ((Heater) device).getHeatingPerHour();
        InnerAtmosphere atmosphere = device.getRoom().getAtmosphere();

        atmosphere.setTemperature(atmosphere.getTemperature()
                + (heatingPerHour / (3600D * 1000000000)) * time);
    }
}
