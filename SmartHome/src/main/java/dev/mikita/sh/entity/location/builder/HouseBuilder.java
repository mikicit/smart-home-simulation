package dev.mikita.sh.entity.location.builder;

import dev.mikita.sh.entity.location.House;
import dev.mikita.sh.entity.sensor.AExternalSensor;
import dev.mikita.sh.entity.sensor.factories.ExternalSensorFactory;

public class HouseBuilder {
    private final House house = new House();

    public HouseBuilder addSensor(String type) {
        ExternalSensorFactory externalSensorFactory = ExternalSensorFactory.getInstance();

        house.addSensor((AExternalSensor) externalSensorFactory.create(type, house));
        return this;
    }

    public FloorBuilder addFloor(int level) {
        return new FloorBuilder(this, house, level);
    }

    public House getResult() {
        return this.house;
    }
}
