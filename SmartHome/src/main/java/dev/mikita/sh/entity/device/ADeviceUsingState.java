package dev.mikita.sh.entity.device;

/**
 * Abstract class representing device's using state
 */
public abstract class ADeviceUsingState extends ADeviceState {
    public ADeviceUsingState(ADevice device) {
        super(device);
    }
}
