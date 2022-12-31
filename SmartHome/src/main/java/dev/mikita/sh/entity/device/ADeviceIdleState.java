package dev.mikita.sh.entity.device;

/**
 * Abstract class representing device's idle state
 */
public abstract class ADeviceIdleState extends ADeviceState {
    /**
     * Instantiates a new A device idle state.
     *
     * @param device the device
     */
    public ADeviceIdleState(ADevice device) {
        super(device);
    }
}
