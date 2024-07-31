package dev.mikita.sh.entity.device.airConditioner.state;

import dev.mikita.sh.entity.device.ADevice;
import dev.mikita.sh.entity.device.ADeviceFixingState;

/**
 * Class representing the Air conditioner fixing state.
 */
public class AirConditionerFixingState extends ADeviceFixingState {
    /**
     * Instantiates a new Air conditioner fixing state.
     *
     * @param device the device
     */
    public AirConditionerFixingState(ADevice device) {
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
