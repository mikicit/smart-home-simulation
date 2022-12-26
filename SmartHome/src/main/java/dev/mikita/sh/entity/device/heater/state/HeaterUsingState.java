package dev.mikita.sh.entity.device.heater.state;

import dev.mikita.sh.core.SHSystem;
import dev.mikita.sh.entity.device.ADevice;
import dev.mikita.sh.entity.device.ADeviceUsingState;
import dev.mikita.sh.entity.device.heater.Heater;
import dev.mikita.sh.entity.location.atmosphere.InnerAtmosphere;

import java.util.logging.Logger;

public class HeaterUsingState extends ADeviceUsingState {
    // Logger
    private static final Logger log = Logger.getLogger(HeaterUsingState.class.getName());

    public HeaterUsingState(ADevice device) {
        super(device);
        this.ELECTRICITY_CONSUMPTION = 1.28;

        log.info(String.format("Heater is working now [%s]",
                SHSystem.getInstance().getTimer().getFormattedTime()));
    }

    @Override
    public void update(long time) {
        this.time += time;

        double heatingPerHour = ((Heater) device).getHeatingPerHour();
        InnerAtmosphere atmosphere = device.getRoom().getAtmosphere();

        // Consumption
        device.setCurrentElectricityConsumption(device.getCurrentElectricityConsumption()
                + (ELECTRICITY_CONSUMPTION / (3600L * 1000000000L)) * time);

        // Temperature
        atmosphere.setTemperature(atmosphere.getTemperature()
                + (heatingPerHour / (3600L * 1000000000L)) * time);
    }
}
