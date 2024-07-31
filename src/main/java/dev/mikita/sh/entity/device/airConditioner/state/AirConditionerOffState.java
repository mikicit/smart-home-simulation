package dev.mikita.sh.entity.device.airConditioner.state;

import dev.mikita.sh.entity.device.ADevice;
import dev.mikita.sh.entity.device.ADeviceOffState;

/**
 * Class representing the Air conditioner off state.
 */
public class AirConditionerOffState extends ADeviceOffState {
    /**
     * Instantiates a new Air conditioner off state.
     *
     * @param device the device
     */
    public AirConditionerOffState(ADevice device) {
        super(device);
    }

    /**
     * Update.
     * @param time the time
     */
    @Override
    public void update(long time) {
        this.time += time;
    }
}
