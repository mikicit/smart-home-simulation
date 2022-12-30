package dev.mikita.sh.entity.device;

/**
 * Abstract class representing device's broken state
 */
public abstract class ADeviceBrokenState extends ADeviceState {
    public ADeviceBrokenState(ADevice device) {
        super(device);
    }
}
