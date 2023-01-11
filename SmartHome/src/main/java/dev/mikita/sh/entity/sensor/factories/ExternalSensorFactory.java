package dev.mikita.sh.entity.sensor.factories;

import dev.mikita.sh.entity.location.ILocation;
import dev.mikita.sh.entity.location.House;
import dev.mikita.sh.entity.sensor.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for creating external sensors.
 */
public class ExternalSensorFactory implements ISensorFactory {
    private static ExternalSensorFactory instance;
    private final List<ASensor> sensors = new ArrayList<>();

    private ExternalSensorFactory() {}

    /**
     * Returns the instance.
     * @return instance
     */
    public static ExternalSensorFactory getInstance() {
        if (instance == null) {
            instance = new ExternalSensorFactory();
        }

        return instance;
    }

    /**
     * Creates a sensor.
     * @param type the type
     * @param house location
     * @return sensor
     */
    public ASensor create(String type, ILocation house) {
        ASensor sensor = switch (type) {
            case "WIND" -> new WindSensor((House) house);
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
