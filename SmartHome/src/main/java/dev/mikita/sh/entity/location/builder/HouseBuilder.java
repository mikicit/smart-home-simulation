package dev.mikita.sh.entity.location.builder;

import dev.mikita.sh.entity.location.House;
import dev.mikita.sh.entity.sensor.AExternalSensor;
import dev.mikita.sh.entity.sensor.factories.ExternalSensorFactory;

/**
 * Class for building the house.
 */
public class HouseBuilder {
    private final House house = new House();

    public HouseBuilder addSensor(String type) {
        ExternalSensorFactory externalSensorFactory = ExternalSensorFactory.getInstance();

        house.addSensor((AExternalSensor) externalSensorFactory.create(type, house));
        return this;
    }

    /**
     * Adds a floor.
     * @param level floor's level
     * @return floor builder
     */
    public FloorBuilder addFloor(int level) {
        return new FloorBuilder(this, house, level);
    }

    /**
     * Returns the house.
     * @return house
     */
    public House getResult() {
        return this.house;
    }
}
