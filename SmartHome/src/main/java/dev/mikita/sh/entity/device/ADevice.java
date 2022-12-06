package dev.mikita.sh.entity.device;

import dev.mikita.sh.core.ITimeTracker;
import dev.mikita.sh.entity.device.state.ADeviceState;

public abstract class ADevice implements IConsumer, ITimeTracker {
    private ADeviceState state;
    private double currentElectricityConsumption = 0;
    private double currentWaterConsumption = 0;
    private double currentGasConsumption = 0;
    private double lastElectricityConsumption = 0;
    private double lastWaterConsumption = 0;
    private double lastGasConsumption = 0;

    public void changeState(ADeviceState state) {
        this.state = state;
    }

    @Override
    public double getCurrentElectricityConsumption() {
        return currentElectricityConsumption;
    }

    @Override
    public double getCurrentWaterConsumption() {
        return currentWaterConsumption;
    }

    @Override
    public double getCurrentGasConsumption() {
        return currentGasConsumption;
    }

    @Override
    public double calculateElectricityConsumption() {
        double result = currentElectricityConsumption - lastElectricityConsumption;
        lastElectricityConsumption = currentElectricityConsumption;

        return result;
    }

    @Override
    public double calculateWaterConsumption() {
        double result = currentWaterConsumption - lastWaterConsumption;
        lastWaterConsumption = currentWaterConsumption;

        return result;
    }

    @Override
    public double calculateGasConsumption() {
        double result = currentGasConsumption - lastGasConsumption;
        lastGasConsumption = currentGasConsumption;

        return result;
    }
}
