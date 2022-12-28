package dev.mikita.sh.entity.device.airConditioner.state;

import dev.mikita.sh.entity.device.ADevice;
import dev.mikita.sh.entity.device.ADeviceUsingState;
import dev.mikita.sh.entity.device.airConditioner.AirConditioner;
import dev.mikita.sh.entity.location.atmosphere.InnerAtmosphere;

public class AirConditionerUsingState extends ADeviceUsingState {
    public AirConditionerUsingState(ADevice device) {
        super(device);
        this.ELECTRICITY_CONSUMPTION = 3.12;
    }

    @Override
    public void update(long time) {
        // Wear out time
        if (device.getTime() > device.getOperatingTimeInHours() * 3600D * 1000000000L) {
            device.changeState(new AirConditionerBrokenState(device));
        }

        this.time += time;
        device.setTime(device.getTime() + time);

        // Consumption
        device.setCurrentElectricityConsumption(device.getCurrentElectricityConsumption()
                + (ELECTRICITY_CONSUMPTION / (3600L * 1000000000L)) * this.time);

        // Temperature
        double coolingPerHour = ((AirConditioner) device).getCoolingPerHour();
        InnerAtmosphere atmosphere = device.getRoom().getAtmosphere();

        atmosphere.setTemperature(atmosphere.getTemperature()
                - (coolingPerHour / (3600L * 1000000000L)) * this.time);
    }
}
