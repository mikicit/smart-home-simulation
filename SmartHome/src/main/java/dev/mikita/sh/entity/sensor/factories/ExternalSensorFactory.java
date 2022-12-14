package dev.mikita.sh.entity.sensor.factories;

import dev.mikita.sh.core.event.ILocation;
import dev.mikita.sh.entity.location.House;
import dev.mikita.sh.entity.sensor.*;

import java.util.ArrayList;
import java.util.List;

public class ExternalSensorFactory implements SensorFactory {
    private static ExternalSensorFactory instance;
    private final List<ASensor> sensors = new ArrayList<>();

    private ExternalSensorFactory() {}

    public static ExternalSensorFactory getInstance() {
        if (instance == null) {
            instance = new ExternalSensorFactory();
        }

        return instance;
    }

    public ASensor create(String type, ILocation house) {
        ASensor sensor = switch (type) {
            case "WIND" -> new WindSensor((House) house);
            default -> throw new IllegalArgumentException("Type of sensor " + type + " was not found.");
        };

        sensors.add(sensor);
        return sensor;
    }

    public List<ASensor> getSensors() {
        return sensors;
    }
}
