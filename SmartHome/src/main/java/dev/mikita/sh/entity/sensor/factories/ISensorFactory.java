package dev.mikita.sh.entity.sensor.factories;

import dev.mikita.sh.entity.location.ILocation;
import dev.mikita.sh.entity.sensor.ASensor;
import java.util.List;

/**
 * Interface representing a sensor factory.
 */
public interface ISensorFactory {
    /**
     * Creates a sensor.
     * @param type the type
     * @param location the location
     * @return sensor
     */
    ASensor create(String type, ILocation location);

    /**
     * Returns the sensors.
     * @return sensors
     */
    List<ASensor> getSensors();
}
