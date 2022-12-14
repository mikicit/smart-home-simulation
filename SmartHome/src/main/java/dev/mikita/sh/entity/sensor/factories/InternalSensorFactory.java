package dev.mikita.sh.entity.sensor.factories;

import dev.mikita.sh.core.event.ILocation;
import dev.mikita.sh.entity.location.Room;
import dev.mikita.sh.entity.sensor.*;

import java.util.ArrayList;
import java.util.List;

public class InternalSensorFactory implements SensorFactory {
    private static InternalSensorFactory instance;
    private final List<ASensor> sensors = new ArrayList<>();

    private InternalSensorFactory() {}

    public static InternalSensorFactory getInstance() {
        if (instance == null) {
            instance = new InternalSensorFactory();
        }

        return instance;
    }

    public ASensor create(String type, ILocation room) {
        ASensor sensor = switch (type) {
            case "HEAT" -> new HeatSensor((Room) room);
            case "LIGHT" -> new LightSensor((Room) room);
            case "SMOKE" -> new SmokeSensor((Room) room);
            default -> throw new IllegalArgumentException("Type of sensor " + type + " was not found.");
        };

        sensors.add(sensor);
        return sensor;
    }

    public List<ASensor> getSensors() {
        return sensors;
    }
}