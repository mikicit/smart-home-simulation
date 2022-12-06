package dev.mikita.sh.entity.device.strategy;

import dev.mikita.sh.entity.device.ADevice;

public abstract class ADeviceStrategy {
    private ADevice device;
    private long time = 0;

    public ADeviceStrategy(ADevice device) {
        this.device = device;
    }

    public abstract void update();
}
