package dev.mikita.sh.core.report;

import dev.mikita.sh.core.SHSystem;
import dev.mikita.sh.entity.device.ADevice;
import dev.mikita.sh.entity.device.DeviceFactory;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ConsumptionReport {
    private FileWriter consumptionReport = null;
    public ConsumptionReport() {
        try {
            this.consumptionReport = new FileWriter("report/ConsumptionReport.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void generateReport() throws IOException {
        List<ADevice> allDevices = new ArrayList<>(DeviceFactory.getInstance().getDevices());

        double totalElectricity = 0;
        double totalWater = 0;
        double totalGas = 0;
        int day = SHSystem.getInstance().getSimulation().getDay();

        consumptionReport.write("__________________________________ Report for the " + day + " day __________________________________\n");

        // Electricity consumption
        consumptionReport.write("_________________ Electricity consumption _________________\n");
        for (ADevice device : allDevices) {

            // TODO не работает выпис у девайсов

            totalElectricity += device.calculateElectricityConsumption();
            consumptionReport.write(device.getName() + " has used " + device.calculateElectricityConsumption() + " KwH " + "electricity for today.\n");
        }
        consumptionReport.write("Total electricity used by day: " + totalElectricity + " KwH.\n");

        // Water consumption
        consumptionReport.write("_________________ Water consumption _________________\n");
        for (ADevice device : allDevices) {
            totalWater += device.calculateWaterConsumption();
            consumptionReport.write(device.getName() + " has used " + device.calculateWaterConsumption() + " litre " + "water for today.\n");
        }
        consumptionReport.write("Total water used by day: " + totalWater + " litre.\n");

        // Gas consumption
        consumptionReport.write("_________________ Gas consumption _________________\n");
        for (ADevice device : allDevices) {
            totalGas += device.calculateGasConsumption();
            consumptionReport.write(device.getName() + " has used " + device.calculateGasConsumption() + " cu. m. " + "gas for today.\n");
        }
        consumptionReport.write("Total gas used by day: " + totalGas + " cu. m.\n");

        consumptionReport.flush();
    }
}
