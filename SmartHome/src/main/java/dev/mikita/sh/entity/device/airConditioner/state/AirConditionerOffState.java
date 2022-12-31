package dev.mikita.sh.entity.device.airConditioner.state;

import dev.mikita.sh.entity.device.ADevice;
import dev.mikita.sh.entity.device.ADeviceOffState;

/**
 * The type Air conditioner off state.
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

    @Override
    public void update(long time) {
        this.time += time;
    }
}
