package dev.mikita.sh.entity.device;

public abstract class ADeviceState {
    protected long time;
    protected ADevice device;

    protected double ELECTRICITY_CONSUMPTION = 0;
    protected double WATER_CONSUMPTION = 0;
    protected double GAS_CONSUMPTION = 0;

    public ADeviceState(ADevice device) {
        this.device = device;
    }

    public double getElectricityConsumption() {
        return ELECTRICITY_CONSUMPTION;
    }

    public double getWaterConsumption() {
        return WATER_CONSUMPTION;
    }

    public double getGasConsumption() {
        return GAS_CONSUMPTION;
    }

    public abstract void update(long time);
}
