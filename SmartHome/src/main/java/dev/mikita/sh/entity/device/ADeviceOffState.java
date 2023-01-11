package dev.mikita.sh.entity.device;

/**
 * Abstract class representing device's off state.
 */
public abstract class ADeviceOffState extends ADeviceState {
    /**
     * Instantiates a new device off state.
     *
     * @param device the device
     */
    public ADeviceOffState(ADevice device) {
        super(device);
    }
}
