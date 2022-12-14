package dev.mikita.sh.entity.device.heater.state;

import dev.mikita.sh.entity.device.ADevice;
import dev.mikita.sh.entity.device.ADeviceState;
import dev.mikita.sh.entity.device.heater.Heater;
import dev.mikita.sh.entity.location.atmosphere.InnerAtmosphere;

public class HeaterUsingState extends ADeviceState {
    public HeaterUsingState(ADevice device) {
        super(device);
        this.ELECTRICITY_CONSUMPTION = 1.28;

        System.out.println("ГРЕЮ БЛЕАТЬ");
    }

    @Override
    public void update(long time) {
        double heatingPerHour = ((Heater) device).getHeatingPerHour();
        InnerAtmosphere atmosphere = device.getRoom().getAtmosphere();

        device.setCurrentElectricityConsumption(device.getCurrentElectricityConsumption() + ELECTRICITY_CONSUMPTION / 3600);
        atmosphere.setTemperature(atmosphere.getTemperature() + heatingPerHour / 3600);
    }
}
