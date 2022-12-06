package dev.mikita.sh.entity.device.state;

import dev.mikita.sh.entity.device.ADevice;
import dev.mikita.sh.entity.device.strategy.ADeviceStrategy;

public abstract class ADeviceState {
    private ADevice device;
    private ADeviceStrategy strategy;
    private double electricityConsumption = 0;
    private double waterConsumption = 0;
    private double gasConsumption = 0;

    public ADeviceState(ADevice device) {
        this.device = device;
    }

    public ADeviceStrategy getStrategy() {
        return strategy;
    }

    public void setStrategy(ADeviceStrategy strategy) {
        this.strategy = strategy;
    }

    public double getElectricityConsumption() {
        return electricityConsumption;
    }

    public void setElectricityConsumption(double electricityConsumption) {
        this.electricityConsumption = electricityConsumption;
    }

    public double getWaterConsumption() {
        return waterConsumption;
    }

    public void setWaterConsumption(double waterConsumption) {
        this.waterConsumption = waterConsumption;
    }

    public double getGasConsumption() {
        return gasConsumption;
    }

    public void setGasConsumption(double gasConsumption) {
        this.gasConsumption = gasConsumption;
    }
}
