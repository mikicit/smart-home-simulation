package dev.mikita.sh.entity.sensor;

import dev.mikita.sh.entity.location.House;

/**
 * Abstract class representing an external sensor.
 */
public abstract class AExternalSensor extends ASensor {
    protected House house;

    public AExternalSensor(House house) {
        this.house = house;
    }

    /**
     * Returns the location.
     * @return house
     */
    public House getHouse() {
        return house;
    }
}
