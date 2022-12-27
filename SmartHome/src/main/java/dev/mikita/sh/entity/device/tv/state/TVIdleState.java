package dev.mikita.sh.entity.device.tv.state;

import dev.mikita.sh.core.SHSystem;
import dev.mikita.sh.entity.device.ADevice;
import dev.mikita.sh.entity.device.ADeviceIdleState;

import java.util.logging.Logger;

public class TVIdleState extends ADeviceIdleState {
    // Logger
    private static final Logger log = Logger.getLogger(TVIdleState.class.getName());

    public TVIdleState(ADevice device) {
        super(device);
        this.ELECTRICITY_CONSUMPTION = 1.28;

        log.info(String.format("TV is not being used now [%s]",
                SHSystem.getInstance().getSimulation().getFormattedTime()));
    }


    @Override
    public void update(long time) {
//        if (device.getTime() > 10L * 1000000000L) {
//            device.changeState(new TVBrokenState(device));
//        }

        device.setTime(device.getTime() + time);
        this.time += time;

        // Consumption
        device.setCurrentElectricityConsumption(device.getCurrentElectricityConsumption()
                + (ELECTRICITY_CONSUMPTION / 3600F * 1000000000) * this.time);
    }
}
