package dev.mikita.sh.entity.device;

/**
 * Abstract class representing device's fixing state.
 */
public abstract class ADeviceFixingState extends ADeviceState {
    /**
     * Instantiates a new device fixing state.
     *
     * @param device the device
     */
    public ADeviceFixingState(ADevice device) {
        super(device);
    }
}
