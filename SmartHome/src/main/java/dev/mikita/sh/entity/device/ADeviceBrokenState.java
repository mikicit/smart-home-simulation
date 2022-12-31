package dev.mikita.sh.entity.device;

/**
 * Abstract class representing device's broken state
 */
public abstract class ADeviceBrokenState extends ADeviceState {
    /**
     * Instantiates a new A device broken state.
     *
     * @param device the device
     */
    public ADeviceBrokenState(ADevice device) {
        super(device);
    }
}
