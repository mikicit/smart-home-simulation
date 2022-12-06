package dev.mikita.sh.entity.device;

public interface IConsumer {
    double getCurrentElectricityConsumption();
    double getCurrentWaterConsumption();
    double getCurrentGasConsumption();
    double calculateElectricityConsumption();
    double calculateWaterConsumption();
    double calculateGasConsumption();
}
