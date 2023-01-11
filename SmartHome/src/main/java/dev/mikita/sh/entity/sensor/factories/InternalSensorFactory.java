package dev.mikita.sh.entity.sensor.factories;

import dev.mikita.sh.entity.location.ILocation;
import dev.mikita.sh.entity.location.Room;
import dev.mikita.sh.entity.sensor.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for creating internal sensors.
 */
public class InternalSensorFactory implements ISensorFactory {
    private static InternalSensorFactory instance;
    private final List<ASensor> sensors = new ArrayList<>();

    private InternalSensorFactory() {}

    /**
     * Returns the instance.
     * @return instance
     */
    public static InternalSensorFactory getInstance() {
        if (instance == null) {
            instance = new InternalSensorFactory();
        }

        return instance;
    }

    /**
     * Creates a sensor.
     * @param type the type
     * @param room location
     * @return sensor
     */
    public ASensor create(String type, ILocation room) {
        ASensor sensor = switch (type) {
            case "HEAT" -> new HeatSensor((Room) room);
            case "LIGHT" -> new LightSensor((Room) room);
            case "SMOKE" -> new SmokeSensor((Room) room);
            case "WATER" -> new WaterSensor((Room) room);
            default -> throw new IllegalArgumentException("Type of sensor " + type + " was not found.");
        };

        sensors.add(sensor);
        return sensor;
    }

    /**
     * Returns the sensors.
     * @return sensors
     */
    public List<ASensor> getSensors() {
        return sensors;
    }
}
