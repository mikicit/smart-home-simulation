package dev.mikita.sh.entity.location;

import dev.mikita.sh.entity.location.atmosphere.OuterAtmosphere;
import dev.mikita.sh.entity.sensor.AExternalSensor;
import java.util.ArrayList;
import java.util.List;

/**
 * Class representing a house.
 */
public class House implements ILocation {
    private final OuterAtmosphere atmosphere = new OuterAtmosphere();
    private final List<Floor> floors = new ArrayList<>();
    private final List<AExternalSensor> sensors = new ArrayList<>();

    /**
     * Adds a floor.
     * @param floor the floor
     */
    public void addFloor(Floor floor) {
        this.floors.add(floor);
    }

    /**
     * Adds an external sensor.
     * @param sensor the sensor
     */
    public void addSensor(AExternalSensor sensor) {
        this.sensors.add(sensor);
    }

    /**
     * Returns the outer atmosphere.
     * @return atmosphere
     */
    public OuterAtmosphere getAtmosphere() {
        return this.atmosphere;
    }

    /**
     * Returns the name.
     * @return name
     */
    public String getName() {
        return "House";
    }

    /**
     * Returns the floors.
     * @return floors
     */
    public List<Floor> getFloors() {
        return floors;
    }

    /**
     * Returns external sensors.
     * @return sensors
     */
    public List<AExternalSensor> getExternalSensors() {
        return sensors;
    }
}
