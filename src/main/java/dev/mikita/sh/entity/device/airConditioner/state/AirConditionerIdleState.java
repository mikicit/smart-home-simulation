package dev.mikita.sh.entity.device.airConditioner.state;

import dev.mikita.sh.core.SHSystem;
import dev.mikita.sh.entity.device.ADevice;
import dev.mikita.sh.entity.device.ADeviceIdleState;
import dev.mikita.sh.entity.device.fridge.state.FridgeIdleState;

import java.util.logging.Logger;

/**
 * Class representing the Air conditioner idle state.
 */
public class AirConditionerIdleState extends ADeviceIdleState {
    // Logger
    private static final Logger log = Logger.getLogger(FridgeIdleState.class.getName());

    /**
     * Instantiates a new Air conditioner idle state.
     *
     * @param device the device
     */
    public AirConditionerIdleState(ADevice device) {
        super(device);
        this.ELECTRICITY_CONSUMPTION = 0.2;

        log.info(String.format("Air Conditioner in room \"%s\" is not being used now [%s]",
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
            device.changeState(new AirConditionerBrokenState(device));
        }

        this.time += time;
        device.setTime(device.getTime() + time);

        // Consumption
        device.setCurrentElectricityConsumption(device.getCurrentElectricityConsumption()
                + (ELECTRICITY_CONSUMPTION / (3600D * 1000000000)) * time);
    }
}
