package dev.mikita.sh.entity.device;

/**
 * Abstract class representing device's using state.
 */
public abstract class ADeviceUsingState extends ADeviceState {
    /**
     * Instantiates a new device using state.
     *
     * @param device the device
     */
    public ADeviceUsingState(ADevice device) {
        super(device);
    }
}
